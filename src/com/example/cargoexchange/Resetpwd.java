package com.example.cargoexchange;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Resetpwd extends Activity {
    private EditText mAccount;                        //鐢ㄦ埛鍚嶇紪杈�
    private EditText mPwd_old;                            //瀵嗙爜缂栬緫
    private EditText mPwd_new;                            //瀵嗙爜缂栬緫
    private EditText mPwdCheck;                       //瀵嗙爜缂栬緫
    private Button mSureButton;                       //纭畾鎸夐挳
    private Button mCancelButton;                     //鍙栨秷鎸夐挳
    private UserDataManager mUserDataManager;         //鐢ㄦ埛鏁版嵁绠＄悊绫�
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpwd);
//        layout.setOrientation(RelativeLayout.VERTICAL).
        mAccount = (EditText) findViewById(R.id.resetpwd_edit_name);
        mPwd_old = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        mPwd_new = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);
        mPwdCheck = (EditText) findViewById(R.id.resetpwd_edit_pwd_check);

        mSureButton = (Button) findViewById(R.id.resetpwd_btn_sure);
        mCancelButton = (Button) findViewById(R.id.resetpwd_btn_cancel);

        mSureButton.setOnClickListener(m_resetpwd_Listener);      //娉ㄥ唽鐣岄潰涓や釜鎸夐挳鐨勭洃鍚簨浠�
        mCancelButton.setOnClickListener(m_resetpwd_Listener);
        //mCancelButton.setOnClickListener(m_resetpwd_Listener);

        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //寤虹珛鏈湴鏁版嵁搴�
        }

    }
    View.OnClickListener m_resetpwd_Listener = new View.OnClickListener() {    //涓嶅悓鎸夐挳鎸変笅鐨勭洃鍚簨浠堕�夋嫨
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.resetpwd_btn_sure:                       //纭鎸夐挳鐨勭洃鍚簨浠�
                    resetpwd_check();
                    break;
                case R.id.resetpwd_btn_cancel:                     //鍙栨秷鎸夐挳鐨勭洃鍚簨浠�,鐢辨敞鍐岀晫闈㈣繑鍥炵櫥褰曠晫闈�
                    Intent intent_Resetpwd_to_Login = new Intent(Resetpwd.this,Login.class) ;    //鍒囨崲Resetpwd Activity鑷矻ogin Activity
                    startActivity(intent_Resetpwd_to_Login);
                    finish();
                    break;
            }
        }
    };
    public void resetpwd_check() {                                //纭鎸夐挳鐨勭洃鍚簨浠�
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();
            String userPwd_old = mPwd_old.getText().toString().trim();
            String userPwd_new = mPwd_new.getText().toString().trim();
            String userPwdCheck = mPwdCheck.getText().toString().trim();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd_old);
            if(result==1){                                             //杩斿洖1璇存槑鐢ㄦ埛鍚嶅拰瀵嗙爜鍧囨纭�,缁х画鍚庣画鎿嶄綔
                if(userPwd_new.equals(userPwdCheck)==false){           //涓ゆ瀵嗙爜杈撳叆涓嶄竴鏍�
                    Toast.makeText(this, getString(R.string.pwd_not_the_same),Toast.LENGTH_SHORT).show();
                    return ;
                } else {
                    UserData mUser = new UserData(userName, userPwd_new);
                    mUserDataManager.openDataBase();
                    boolean flag = mUserDataManager.updateUserData(mUser);
                    if (flag == false) {
                        Toast.makeText(this, getString(R.string.resetpwd_fail),Toast.LENGTH_SHORT).show();
                    }else{

                        Toast.makeText(this, getString(R.string.resetpwd_success),Toast.LENGTH_SHORT).show();

                        mUser.pwdresetFlag=1;
                        Intent intent_Register_to_Login = new Intent(Resetpwd.this,Login.class) ;    //鍒囨崲User Activity鑷矻ogin Activity
                        startActivity(intent_Register_to_Login);
                        finish();
                    }
                }
            }else if(result==0){                                       //杩斿洖0璇存槑鐢ㄦ埛鍚嶅拰瀵嗙爜涓嶅尮閰嶏紝閲嶆柊杈撳叆
                Toast.makeText(this, getString(R.string.pwd_not_fit_user),Toast.LENGTH_SHORT).show();
                return;
            }




        }
    }
    public boolean isUserNameAndPwdValid() {
        String userName = mAccount.getText().toString().trim();
        //妫�鏌ョ敤鎴锋槸鍚﹀瓨鍦�
        int count=mUserDataManager.findUserByName(userName);
        //鐢ㄦ埛涓嶅瓨鍦ㄦ椂杩斿洖锛岀粰鍑烘彁绀烘枃瀛�
        if(count<=0){
            Toast.makeText(this, getString(R.string.name_not_exist, userName),Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty),Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd_old.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty),Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd_new.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_new_empty),Toast.LENGTH_SHORT).show();
            return false;
        }else if(mPwdCheck.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_check_empty),Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}

