//import java.util.*;
//
//class Log{
//    String clientId;
//    String userName;
//    Date loginTimestamp;
//
//    public Long getClientId() {
//        return clientId;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public Date getLoginTimestamp() {
//        return loginTimestamp;
//    }
//}
//
////11，张三，2020-03-15：00：00：00
////        12，李四，2020-03-16：00：00：01
////        11，张三，2020-03-17：00：00：02
////        12，李四，2020-03-16：00：00：03
////        ....
//
//public  class UserLogInfo {
//    public List<Long> statistics(List<Log> logs) {
//        //TODO  (登陆最频繁的10个用户，排序并打印出这10个用户各登陆多少次
//        if (logs == null || logs.size() == 0) {
//            return null;
//        }
//
//        Calendar ca = Calendar.getInstance();
//        Date todaytime = ca.getTime();
//        ca.setTime(todaytime);
//        int today = ca.get(Calendar.DAY_OF_YEAR);
//
//        Map<Long, Integer> userIds = new HashMap<>();
//        for (Log u : logs) {
//            ca.setTime(u.getLoginTimestamp());
//            int otherDay = ca.get(Calendar.DAY_OF_YEAR);
//            if ((today - otherDay) >= 0 && (today - otherDay) <= 9) {
//                if (!userIds.containsKey(u.getClientId())) {
//                    userIds.put(u.getClientId(), 1);
//                } else {
//                    userIds.put(u.getClientId(), userIds.get(u.getClientId()) + 1);
//                }
//            }
//        }
//
//
//        Map<Long, Integer> sortUserIds = sortByValue(userIds);
//        List<Long> result = new ArrayList<>();
//        List<Long> keylist = new ArrayList<>(sortUserIds.keySet());
//        for (int i = 0; i < 10; i++) {
//            result.add(keylist.get(i));
//        }
//        return result;
//    }
//}
//
//
//
//
////
////        int temp= 0;
////        boolean flag =false;
////
////        for(int j=0;j<10;j++){
////            for(int i=0;i<10;i++){
////                if(logs[i]>logs[i+1]){
////                    flag=true;
////                    temp=logs[i]
////                    logs[i]=logs[j+1];
////                    logs[i+1]=temp;
////
////                }
////            }
////            System.out.println(Arrays.toString(logs));
////            if(flag){
////                flag=false;
////            }else{
////                break;
////            }
////        }
////    }
////}