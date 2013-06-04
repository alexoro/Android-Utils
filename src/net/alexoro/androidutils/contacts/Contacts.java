package net.alexoro.androidutils.contacts;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//TODO not finished yet
public class Contacts {
	
	public static class Info {
		public long id;
		public String name;
		public List<String> phoneNumbers;
		public List<String> emails;
		public Bitmap photo;
	}

    private Context mContext;

    public Contacts(Context context) {
        mContext = context;
    }

	public List<Info> getContactInfo(String phoneNumber) {
		List<Info> r = new ArrayList<Info>();

		Cursor cursor = mContext.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
                null,
                ContactsContract.CommonDataKinds.Phone.NUMBER + " = ?", 
                new String[] { phoneNumber },
                null
		);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                r.add(getContactInfoFromCursor(cursor));
            }
            cursor.close();
        }

		return r;
	}
	
	
	public Info getContactInfo(Context ctx, long contactId) {
		Cursor cursor = ctx.getContentResolver().query( 
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
                new String[] {
						ContactsContract.Contacts._ID,
						ContactsContract.Contacts.DISPLAY_NAME,
						ContactsContract.Contacts.HAS_PHONE_NUMBER
				},
                ContactsContract.CommonDataKinds.Phone._ID + " = ?", 
                new String[] {
                		String.valueOf(contactId)
                },
                null
		);

		if (cursor != null && cursor.moveToFirst() ) {
            Info r = getContactInfoFromCursor(cursor);
            cursor.close();
            return r;
		} else {
			return null;
		}
	}

    public Bitmap getContactPhoto(long contactId) {
        Uri photoUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(
                mContext.getContentResolver(),
                photoUri
        );

        Bitmap r = null;
        try {
            if (input != null) {
                r = BitmapFactory.decodeStream(input);
                input.close();
            }
        } catch (Exception ex) {
            Log.w(Contacts.class.getSimpleName(), ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {}
            }
        }

        return r;
    }
	
	
	protected Info getContactInfoFromCursor(Cursor cursor) {
		Info info = new Info();

		do {
			info.id = cursor.getLong(
					cursor.getColumnIndex(ContactsContract.Contacts._ID)
			);
			info.name = cursor.getString(
					cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
			);
			info.phoneNumbers = new ArrayList<String>();
			info.emails = new ArrayList<String>();
			info.photo = getContactPhoto(info.id);

			// getting phone
			String hasPhone = cursor.getString(
					cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
			);

			if (hasPhone.equals("1")) {
				Cursor phones = mContext.getContentResolver().query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER },
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + info.id,
						null,
						null
				);

				if (phones != null) {
					for(boolean i = phones.moveToFirst(); i; i = phones.moveToNext()) {
						info.phoneNumbers.add(phones.getString(0).replace("-", ""));
					}
					phones.close();
				}
			} // <-- if( hasPhone.equals("1") )


			Cursor emails = mContext.getContentResolver().query(
					ContactsContract.CommonDataKinds.Email.CONTENT_URI,
					new String[] { ContactsContract.CommonDataKinds.Phone.DATA },
					ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + info.id,
					null,
					null
			); 

			if (emails != null) {
				for (boolean i = emails.moveToFirst(); i; i = emails.moveToNext()) {
					info.emails.add(emails.getString(0));
				}
				emails.close();
			}
		} while( cursor.moveToNext() );

		return info;
	}

	/**
	 * Find contact name and number by not-full number
	 * @param ctx
	 * @param number
	 * @return String[2] = { 0: number, 1: name }, number & name eq null if nothing was found
	 */
	/*public static String[] findContactNameByPhoneNumber(Context ctx, String number) {
		String[] r = null;

		String to_search = Uri.encode(number);
		if( to_search.length() > 7 ) {
			to_search = to_search.substring(to_search.length()-8, to_search.length());
		}

	    ContentResolver resolver = ctx.getContentResolver();
	    Uri lookupUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, to_search);   
	    String[] phoneNoProjections = { PhoneLookup.NUMBER, PhoneLookup.DISPLAY_NAME };
	    Cursor cursor = resolver.query(lookupUri, phoneNoProjections, null, null, null);   
	    try {
	    	cursor.moveToFirst();
	    	r = new String[] {
		    		cursor.getString(0),
		    		cursor.getString(1)
	    	};
	    } catch(Exception ex) {
	    	try {
		    	String name = mFindContactNameByPhoneNumberDeepSearch(ctx, number);
		    	r = (name == null)
		    			? new String[] {null, null }
		    			: new String[] {number, name }
		    	;
	    	} catch(Exception e) {
	    		if (cursor != null) cursor.close();
	    	}
	    } finally {
	        if (cursor != null) cursor.close();
	    }
	    return r;
	}


	private static String mFindContactNameByPhoneNumberDeepSearch(Context ctx, String number) {
		// we will search only by last 6 digits
		String to_search;
		if( number.length() <= 5 ) {
			to_search = number;
		} else {
			to_search = number.substring(
					number.length() - 6,
					number.length()
			);
		}

		String where = String.format(
			"replace(%s, '-', '') LIKE '%%%s'",
			ContactsContract.CommonDataKinds.Phone.NUMBER,
			to_search
		);

		// You know it has a number so now query it like this
		Cursor phones = ctx.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				new String[] { ContactsContract.CommonDataKinds.Phone.CONTACT_ID },
				//ContactsContract.CommonDataKinds.Phone.NUMBER + " LIKE '%"+to_search+"'",
				where,
				null,
				null
		);

		if( phones != null ) {
			for(boolean phone = phones.moveToFirst(); phone; phone = phones.moveToNext()) {
				String contactId = phones.getString(0);
	
				Cursor contact = ctx.getContentResolver().query(
						ContactsContract.Contacts.CONTENT_URI,
						new String[] { ContactsContract.Contacts.DISPLAY_NAME },
						ContactsContract.Contacts._ID + " = ?",
						new String[] {contactId},
						null
				);
				if( contact != null ) {
					if( contact.moveToFirst() ) return contact.getString(0);
					contact.close();
				}
			}
			phones.close();
		}

		return null;
	}*/

}