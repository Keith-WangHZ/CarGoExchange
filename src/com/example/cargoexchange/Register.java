package com.example.cargoexchange;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
    private EditText mAccount;                        //鐢ㄦ埛鍚嶇紪杈�
    private EditText mPwd;                            //瀵嗙爜缂栬緫
    private EditText mPwdCheck;                       //瀵嗙爜缂栬緫
    private Button mSureButton;                       //纭畾鎸夐挳
    private Button mCancelButton;                     //鍙栨秷鎸夐挳
    private UserDataManager mUserDataManager;         //鐢ㄦ埛鏁版嵁绠＄悊绫�
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAccount = (EditText) findViewById(R.id.resetpwd_edit_name);
        mPwd = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        mPwdCheck = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);

        mSureButton = (Button) findViewById(R.id.register_btn_sure);
        mCancelButton = (Button) findViewById(R.id.register_btn_cancel);

        mSureButton.setOnClickListener(m_register_Listener);      //娉ㄥ唽鐣岄潰涓や釜鎸夐挳鐨勭洃鍚簨浠�
        mCancelButton.setOnClickListener(m_register_Listener);

        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //寤虹珛鏈湴鏁版嵁搴�
        }

    }
    View.OnClickListener m_register_Listener = new View.OnClickListener() {    //涓嶅悓鎸夐挳鎸変笅鐨勭洃鍚簨浠堕�夋嫨
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.register_btn_sure:                       //纭鎸夐挳鐨勭洃鍚簨浠�
                    register_check();
                    break;
                case R.id.register_btn_cancel:                     //鍙栨秷鎸夐挳鐨勭洃鍚簨浠�,鐢辨敞鍐岀晫闈㈣繑鍥炵櫥褰曠晫闈�
                    Intent intent_Register_to_Login = new Intent(Register.this,Login.class) ;    //鍒囨崲User Activity鑷矻ogin Activity
                    startActivity(intent_Register_to_Login);
                    finish();
                    break;
            }
        }
    };
    public void register_check() {                                //纭鎸夐挳鐨勭洃鍚簨浠�
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();
            String userPwd = mPwd.getText().toString().trim();
            String userPwdCheck = mPwdCheck.getText().toString().trim();
            //妫�鏌ョ敤鎴锋槸鍚﹀瓨鍦�
            int count=mUserDataManager.findUserByName(userName);
            //鐢ㄦ埛宸茬粡瀛樺湪鏃惰繑鍥烇紝缁欏嚭鎻愮ず鏂囧瓧
            if(count>0){
                Toast.makeText(this, getString(R.string.name_already_exist, userName),Toast.LENGTH_SHORT).show();
                return ;
            }
            if(userPwd.equals(userPwdCheck)==false){     //涓ゆ瀵嗙爜杈撳叆涓嶄竴鏍�
                Toast.makeText(this, getString(R.string.pwd_not_the_same),Toast.LENGTH_SHORT).show();
                return ;
            } else {
                UserData mUser = new UserData(userName, userPwd);
                mUserDataManager.openDataBase();
                long flag = mUserDataManager.insertUserData(mUser); //鏂板缓鐢ㄦ埛淇℃伅
                if (flag == -1) {
                    Toast.makeText(this, getString(R.string.register_fail),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, getString(R.string.register_success),Toast.LENGTH_SHORT).show();
                    Intent intent_Register_to_Login = new Intent(Register.this,Login.class) ;    //鍒囨崲User Activity鑷矻ogin Activity
                    startActivity(intent_Register_to_Login);
                    finish();
                }
            }
        }
    }
    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if(mPwdCheck.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_check_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
