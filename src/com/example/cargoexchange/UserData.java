package com.example.cargoexchange;
/**
 * Created by FoolishFan on 2016/7/14.
 */

public class UserData {
    private String userName;                  //鐢ㄦ埛鍚�
    private String userPwd;                   //鐢ㄦ埛瀵嗙爜
    private int userId;                       //鐢ㄦ埛ID鍙�
    public int pwdresetFlag=0;
    //鑾峰彇鐢ㄦ埛鍚�
    public String getUserName() {             //鑾峰彇鐢ㄦ埛鍚�
        return userName;
    }
    //璁剧疆鐢ㄦ埛鍚�
    public void setUserName(String userName) {  //杈撳叆鐢ㄦ埛鍚�
        this.userName = userName;
    }
    //鑾峰彇鐢ㄦ埛瀵嗙爜
    public String getUserPwd() {                //鑾峰彇鐢ㄦ埛瀵嗙爜
        return userPwd;
    }
    //璁剧疆鐢ㄦ埛瀵嗙爜
    public void setUserPwd(String userPwd) {     //杈撳叆鐢ㄦ埛瀵嗙爜
        this.userPwd = userPwd;
    }
    //鑾峰彇鐢ㄦ埛id
    public int getUserId() {                   //鑾峰彇鐢ㄦ埛ID鍙�
        return userId;
    }
    //璁剧疆鐢ㄦ埛id
    public void setUserId(int userId) {       //璁剧疆鐢ㄦ埛ID鍙�
        this.userId = userId;
    }

   /* public UserData(String userName, String userPwd, int userId) {    //鐢ㄦ埛淇℃伅
        super();
        this.userName = userName;
        this.userPwd = userPwd;
        this.userId = userId;
    }*/

    public UserData(String userName, String userPwd) {  //杩欓噷鍙噰鐢ㄧ敤鎴峰悕鍜屽瘑鐮�
        super();
        this.userName = userName;
        this.userPwd = userPwd;
    }

}
