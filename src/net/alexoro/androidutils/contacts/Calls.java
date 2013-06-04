package net.alexoro.androidutils.contacts;

/**
 * User: UAS
 * Date: 05.06.13
 * Time: 2:04
 */

//TODO not implemented yet
public class Calls {

    /*public class Call {

        public static final int TYPE_INCOMING = 1;
        public static final int TYPE_OUTGOING = 2;
        public static final int TYPE_MISSED = 3;


        public int type;
        public String number;
        public String name;
        public Date timestamp;


        public Call(int type, String number, String name, Date timestamp) {
            this.type = type;
            this.number = number;
            this.name = name;
            this.timestamp = timestamp;
        }
    }*/

    /*@Override
    public String getMissedCalls() {
        String where = String.format(
                "%s = %s AND strftime('%%s', 'now')*1000 - %s < %d",
                CallLog.Calls.TYPE, CallLog.Calls.MISSED_TYPE,
                CallLog.Calls.DATE, Common.MISSED_UNREAD_DIFF_PERIOD
        );
        Cursor c = getContext().getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                new String[] {
                        CallLog.Calls.CACHED_NAME,
                        CallLog.Calls.NUMBER
                },
                where,
                null,
                CallLog.Calls.DATE + " DESC"
        );

        if( c == null || c.moveToFirst() == false ) {
            if( c != null ) c.close();
            return(getContext().getResources().getString(R.string.response_no_missed_calls));

        } else {
            CallsDistinctCounter cdc = new CallsDistinctCounter();

            for(boolean i = c.moveToFirst(); i; i = c.moveToNext()) {
                String name = c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NAME));
                String number = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)).replace("-", "");

                if( name == null ) {
                    name = Common.findContactNameByPhoneNumber(getContext(), number)[1];
                }

                cdc.addItem(Call.TYPE_MISSED, number, name);
            }
            c.close();

            List<String> missed = new ArrayList<String>();
            String val;
            for(CallsDistinctCounter.CallInfo call: cdc.getList()) {
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
                missed.add(val);
            }

            return(
                    getContext().getResources().getString(
                            R.string.response_missed_calls,
                            ImplodeUtils.implode(missed, ";")
                    )
            );
        }
    }*/

    /*@Override
    public String getCalls(int hours) {
        String where = String.format(
                "strftime('%%s', 'now')*1000 - %s < %d",
                CallLog.Calls.DATE, hours*3600*1000
        );

        Cursor c = getContext().getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                new String[] {
                        CallLog.Calls.CACHED_NAME,
                        CallLog.Calls.NUMBER,
                        CallLog.Calls.TYPE
                },
                where,
                null,
                CallLog.Calls.DATE + " DESC"
        );


        if( c == null || c.moveToFirst() == false ) {
            c.close();
            return(getContext().getResources().getString(R.string.response_no_calls, hours));

        } else {
            CallsDistinctCounter cdc = new CallsDistinctCounter();

            do {
                int type = c.getInt(c.getColumnIndex(CallLog.Calls.TYPE));
                String number = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)).replace("-", "");
                String name = c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NAME));
                if( name == null ) {
                    name = Common.findContactNameByPhoneNumber(getContext(), number)[1];
                }
                cdc.addItem(type, number, name);
            } while( c.moveToNext() );
            c.close();


            List<String> incoming = new ArrayList<String>();
            List<String> outgoing = new ArrayList<String>();
            List<String> missed   = new ArrayList<String>();

            String s;
            for(CallsDistinctCounter.CallInfo call: cdc.getList()) {
                if( call.name != null ) {
                    s = getContext().getResources().getString(R.string.mask_distinct_count_known)
                            .replace("%count%", String.valueOf(call.count))
                            .replace("%name%", call.name)
                            .replace("%number%", call.number)
                    ;
                } else {
                    s = getContext().getResources().getString(R.string.mask_distinct_count_unknown)
                            .replace("%count%", String.valueOf(call.count))
                            .replace("%number%", call.number)
                    ;
                }

                switch(call.type) {
                    case CallLog.Calls.INCOMING_TYPE: incoming.add(s); break;
                    case CallLog.Calls.OUTGOING_TYPE: outgoing.add(s); break;
                    case CallLog.Calls.MISSED_TYPE:   missed.add(s); break;
                }
            }


            StringBuilder sb = new StringBuilder();
            if( incoming.size() > 0 ) {
                sb.append(getContext().getResources().getString(
                        R.string.response_incoming_calls,
                        ImplodeUtils.implode(incoming, ";")
                )).append("\n");
            }
            if( outgoing.size() > 0 ) {
                sb.append(getContext().getResources().getString(
                        R.string.response_outgoing_calls,
                        ImplodeUtils.implode(outgoing, ";")
                )).append("\n");
            }
            if( missed.size() > 0 ) {
                sb.append(getContext().getResources().getString(
                        R.string.response_missed_calls,
                        ImplodeUtils.implode(missed, ";")
                ));
            }

            return(sb.toString());
        }

    }*/

    /*class CallsDistinctCounter {
        public class CallInfo {
            int count;
            int type;
            String name;
            String number;
            public CallInfo(int type, String number, String name, int count) {
                this.type = type;
                this.count = count;
                this.name = name;
                this.number = number;
            }
        }

        private List<CallInfo> mCalls;

        public CallsDistinctCounter() {
            mCalls = new ArrayList<CallInfo>();
        }

        public List<CallInfo> getList() {
            return mCalls;
        }

        public void addItem(int type, String number, String name) {
            for(CallInfo c: mCalls) {
                if( c.number.equals(number) && c.type == type ) {
                    c.count++;
                    return;
                }
            }
            mCalls.add(new CallInfo(type, number, name, 1));
        }
    }*/

}