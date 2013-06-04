package net.alexoro.androidutils.contacts;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO not finished yet
public class SMS {

	public static class Message {
		enum Destination {
			INCOMING, OUTGOING
		}
		
		public Date date;
		public String message;
		public String phoneNumber;
		public Contacts.Info contact;
		public Destination destination;
	}

	public static List<Message> getLastSms(Context context, int limit) throws Exception {
		List<Message> result = new ArrayList<Message>(limit);

		/** columns
		0: _id
		1: thread_id
		2: address
		3: person
		4: date
		5: protocol
		6: read   
		7: status
		8: type
		9: reply_path_present
		10: subject
		11: body
		12: service_center
		13: locked
		 */
		Cursor c = context.getContentResolver().query(
				Uri.parse("content://sms"),
				new String[] { "address", "person", "body", "date", "protocol" },
				null,
				null,
				"date DESC"
		);

        Contacts contacts = new Contacts(context);
		int offset = 0;
		if (c != null && c.moveToFirst() == true) {
			do {
				Message m = new Message();
				m.date = new Date(Long.parseLong(
						c.getString(c.getColumnIndex("date"))
				));
				m.message = c.getString(c.getColumnIndex("body"));
				m.phoneNumber = c.getString(c.getColumnIndex("address")).replace("-", "");
				m.contact = contacts.getContactInfo(m.phoneNumber).get(0);
				m.destination = c.getString(c.getColumnIndex("protocol")) == null
						? Message.Destination.OUTGOING
						: Message.Destination.INCOMING
				;
				//m.contact = null;

				result.add(m);

			} while ( offset++ < limit && c.moveToNext() );
		}

		if( c!= null ) c.close();
		return result;

	} // <-- public void getLastSms()


    /*public String getUnreadSms() {
        String where = String.format(
                "read = 0 AND date > %d",
                (new Date()).getTime() - Common.MISSED_UNREAD_DIFF_PERIOD
        );
        Cursor c = getContext().getContentResolver().query(
                Uri.parse("content://sms/inbox"),
                new String[] { "address", "person", "body" },
                where,
                null,
                "date DESC"
        );


        if( c == null || c.moveToFirst() == false ) {
            if( c != null ) c.close();
            return(getContext().getResources().getString(R.string.response_no_unread_sms));

        } else {
            SmsDistinctCounter sdc = new SmsDistinctCounter();

            do {
                String address = c.getString(c.getColumnIndex("address")).replace("-", "");
                String person = c.getString(c.getColumnIndex("person"));
                if( person == null ) {
                    person = Common.findContactNameByPhoneNumber(getContext(), address)[1];
                }

                sdc.addItem(SmsMessage.TYPE_INCOMING, address, person);
            } while (c.moveToNext());
            c.close();


            List<String> ls = new ArrayList<String>();
            String val;
            for(SmsDistinctCounter.SmsInfo call: sdc.getList()) {
                if( call.name != null ) {
                    val = getContext().getResources().getString(R.string.mask_distinct_count_known)
                            .replace("%count%", String.valueOf(call.count))
                            .replace("%name%", call.name)
                            .replace("%number%", call.number)
                    ;
                } else {
                    val = getContext().getResources().getString(R.string.mask_distinct_count_unknown)
                            .replace("%count%", String.valueOf(call.count))
                            .replace("%number%", call.number)
                    ;
                }
                ls.add(val);
            }

            return(
                    getContext().getResources().getString(
                            R.string.response_unread_sms,
                            ImplodeUtils.implode(ls, ";")
                    )
            );
        }
    }*/

    /*public String getSms(int hours) {
        long date_from = (new Date()).getTime() - hours*3600000;

        List<SmsMessage> sms_list = SmsMessage.getMessages(getContext(), SmsMessage.TYPE_BOTH, date_from);

        if( sms_list.size() == 0 ) {
            return(
                    getContext().getResources().getString(R.string.response_no_sms, hours)
            );
        }


        SmsDistinctCounter sdc = new SmsDistinctCounter();
        for(SmsMessage m: sms_list) {
            sdc.addItem(m.type, m.number, m.name);
        }


        List<String> s_in = new ArrayList<String>();
        List<String> s_out = new ArrayList<String>();
        String s;
        for(SmsDistinctCounter.SmsInfo sms: sdc.getList()) {
            if( sms.name != null ) {
                s = getContext().getResources().getString(R.string.mask_distinct_count_known)
                        .replace("%count%", String.valueOf(sms.count))
                        .replace("%name%", sms.name)
                        .replace("%number%", sms.number)
                ;
            } else {
                s = getContext().getResources().getString(R.string.mask_distinct_count_unknown)
                        .replace("%count%", String.valueOf(sms.count))
                        .replace("%number%", sms.number)
                ;
            }

            if( sms.type == SmsMessage.TYPE_INCOMING ) {
                s_in.add(s);
            } else {
                s_out.add(s);
            }
        }


        // responsing
        StringBuilder sb = new StringBuilder();
        if( s_in.size() > 0 ) {
            sb.append(getContext().getResources().getString(
                    R.string.response_incoming_sms,
                    ImplodeUtils.implode(s_in, ";")
            ))
                    .append("\n");
        }
        if( s_out.size() > 0 ) {
            sb.append(getContext().getResources().getString(
                    R.string.response_outgoing_sms,
                    ImplodeUtils.implode(s_out, ";")
            ))
                    .append("\n");
        }

        return(sb.toString());
    }*/

    /*
    	class SmsDistinctCounter {
		public class SmsInfo {
			int count;
			int type;
			String name;
			String number;
			public SmsInfo(int type, String number, String name, int count) {
				this.type = type;
				this.count = count;
				this.name = name;
				this.number = number;
			}
		}

		private List<SmsInfo> mSms;

		public SmsDistinctCounter() {
			mSms = new ArrayList<SmsInfo>();
		}

		public List<SmsInfo> getList() {
			return mSms;
		}

		public void addItem(int type, String number, String name) {
			for(SmsInfo c: mSms) {
				if( c.number.equals(number) && c.type == type ) {
					c.count++;
					return;
				}
			}
			mSms.add(new SmsInfo(type, number, name, 1));
		}
	}
     */

    /*
    public class SmsMessage {

	public static final int TYPE_BOTH = 0;
	public static final int TYPE_INCOMING = 1;
	public static final int TYPE_OUTGOING = 2;


	public static final int CHECK_RESULT_OK = 0;
	public static final int CHECK_RESULT_FAILURE = 1;
	public static final int CHECK_RESULT_EMPTY_BOX = 2;


	public int type;
	public String number;
	public String name;
	public String content;
	public Date timestamp;

	public SmsMessage(int type, String number, String name, String content, Date timestamp) {
		this.type = type;
		this.name = name;
		this.number = number;
		this.content = content;
		this.timestamp = timestamp;
	}



	public static List<SmsMessage> getMessages(Context ctx, int type, long datetime_from) {
		List<SmsMessage> r = new ArrayList<SmsMessage>();

		Uri uri = null;
		switch(type) {
		case TYPE_BOTH:
			uri = Uri.parse("content://sms/"); break;
		case TYPE_INCOMING:
			uri = Uri.parse("content://sms/inbox"); break;
		case TYPE_OUTGOING:
			uri = Uri.parse("content://sms/sent"); break;
		}


		String where = (datetime_from == -1)
				? ""
				: "date > " + datetime_from
		;
		Cursor c = ctx.getContentResolver().query(
				uri,
				new String[] { "protocol", "date", "address", "person", "body" },
				where,
				null,
				"date ASC"
		);


		if( c == null || c.moveToFirst() == false ) {
			if( c != null ) c.close();
		} else {
			do {
				String protocol = c.getString(c.getColumnIndex("protocol"));
				String number = c.getString(c.getColumnIndex("address")).replace("-", "");
				//String person = c.getString(c.getColumnIndex("person"));
				String name = Common.findContactNameByPhoneNumber(ctx, number)[1];
				String message = c.getString(c.getColumnIndex("body"));
				Date date = new Date(Long.valueOf(
					c.getString(c.getColumnIndex("date"))
				));

				int sms_type = -1;
				switch(type) {
					case TYPE_BOTH:
						sms_type = (protocol == null) ? TYPE_OUTGOING : TYPE_INCOMING;
						break;
					default:
						sms_type = type;
						break;
				}

				r.add(new SmsMessage(
						sms_type,
						number,
						name,
						message,
						date
				));
			} while (c.moveToNext());
			c.close();
		}
		return r;
	} // <-- public List<SmsMessage> getMessages()


	public static int checkIsMessagesAvailable(Context ctx, int type) {
		Uri uri = null;
		switch(type) {
		case TYPE_BOTH:
			uri = Uri.parse("content://sms/"); break;
		case TYPE_INCOMING:
			uri = Uri.parse("content://sms/inbox"); break;
		case TYPE_OUTGOING:
			uri = Uri.parse("content://sms/sent"); break;
		}

		Cursor c = ctx.getContentResolver().query(
				uri,
				new String[] { "protocol", "date", "address", "person", "body" },
				null,
				null,
				null
		);


		if( c == null ) {
			return CHECK_RESULT_FAILURE;
		} else if( c.moveToFirst() == false ) {
			c.close();
			return CHECK_RESULT_EMPTY_BOX;
		} else {
			c.close();
			return CHECK_RESULT_OK;
		}
	}

}
     */

    /*
    public static void sendSms(String phoneNumber, String message) {
    	SmsManager sms = SmsManager.getDefault();

    	ArrayList<String> parts = sms.divideMessage(message);
        if( parts.size() > 3 ) {
        	parts = new ArrayList<String>(parts.subList(0, 3));
        	parts.set(
        			2,
        			parts.get(2).substring(0, parts.get(2).length() - 3) + "..."
        	);
        }

    	Logger.d("sendSms(): Sending to " + phoneNumber + " SMS: " + message);

    	try {
    		//sms.sendTextMessage(phoneNumber, null, message, null, null);
    		sms.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
    	} catch(Exception e) {
    		Logger.e("Cannot send SMS: " + e.getMessage(), e);
    	}
    }
     */

}