package vn.s3corp.android.MobileMap.UI.Activities;


/**
 * The local content provider for Mobile Map data.
 * 
 * @author anh.tran
 * @version 0.1.0
 * 
 */

import java.util.ArrayList;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Email;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.PhoneNumber;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.User;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.InvalidDataException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.SystemException;
import vn.s3corp.android.MobileMap.UI.MobileMapApp;
import vn.s3corp.android.MobileMap.UI.R;
import android.R.drawable;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;

public class ViewUserInformationActivity extends Activity {
    
        private TableLayout tablePhone;
        private TableLayout tableEmail;
        private EditText editLoginName;
        private EditText editPassword;
        private EditText editPasswordConfirm;
        private EditText editAddress;
        private User user;    
        private ContentManager contentManager;
        private MobileMapApp app;

        
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view_user_information);
            
            this.setTitle("View and Edit User Information");
            
            tablePhone = (TableLayout) findViewById(R.id.dialog_login_register_table_Phone);
            tableEmail = (TableLayout) findViewById(R.id.dialog_login_register_table_Email);
           
            editLoginName = (EditText) findViewById(R.id.dialog_login_register_edit_loginname);
            editPassword = (EditText) findViewById(R.id.dialog_login_register_edit_password);
            editPasswordConfirm = (EditText) findViewById(R.id.dialog_login_register_edit_password_confirm);
            editAddress = (EditText) findViewById(R.id.dialog_login_register_edit_address);
                        
            app = (MobileMapApp) this.getApplication();
            try
            {
                contentManager = app.getContentManager();
                user = contentManager.getUser(app.getLoginUserId());                
                setUserInformation();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }          
            
        }
        
        private void setUserInformation()
        {
            editLoginName.setText(this.user.getLoginname());
            editPassword.setText(this.user.getPassword());
            editPasswordConfirm.setText(this.user.getPassword());
            editAddress.setText(this.user.getAddress().getAddressString());
            
            // Add email list
            for(Email email:this.user.getEmails())
                addRow(email.getEmailString(), this.tableEmail, mRemoveEmailListener, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

            // Add phone list
            for(PhoneNumber phone:this.user.getPhoneNumbers())
                addRow(phone.getPhoneNumberString(), this.tablePhone, mRemovePhoneListener, InputType.TYPE_CLASS_PHONE);
        }
        
        public void onbtClick(View v) 
        {
            switch(v.getId()){
            case R.id.dialog_login_register_btPhoneAdd:
                addRow("", tablePhone, mRemovePhoneListener, InputType.TYPE_CLASS_PHONE);
                break;
            case R.id.dialog_login_register_btEmailAdd:
                addRow("", tableEmail, mRemoveEmailListener, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;    
            case R.id.dialog_login_register_button_ok:
                if ("".equals(editLoginName.getText().toString())) {
                    Toast.makeText(this, "Login Name is empty.", Toast.LENGTH_SHORT).show();
                    break;
                }

                if ("".equals(editPassword.getText().toString())) {
                    Toast.makeText(this, "Password is empty.", Toast.LENGTH_SHORT).show();
                    break;
                }

                if (!editPassword.getText().toString()
                        .equals(editPasswordConfirm.getText().toString())) {
                    Toast.makeText(this, "Confirm password is different from password.",
                            Toast.LENGTH_SHORT).show();
                    break;
                    }                              
                
                user.getAddress().setAddressString(editAddress.getText().toString());
                user.setLoginname(this.editLoginName.getText().toString());
                user.setPassword(this.editPassword.getText().toString());
                
                // Add email
                user.removeAllEmails();
                for(String emailValue:getAllValueInTable(this.tableEmail))
                {
                    if(!"".equals(emailValue))
                    {
                        Email email = new Email();
                        email.setEmailString(emailValue);
                        user.addEmail(email);
                    }
                }
                
                // Add phone
                user.removeAllPhoneNumbers();
                for(String phoneValue:getAllValueInTable(this.tablePhone))
                {
                    if(!"".equals(phoneValue))
                    {
                        PhoneNumber phone = new PhoneNumber();
                        phone.setPhoneNumberString(phoneValue);
                        user.addPhoneNumber(phone);
                    }                   
                }
                
                try{
                    
                    if(contentManager == null)
                        contentManager = app.getContentManager();
                    
                    boolean isSuccessful = contentManager.updateUser(user);    
                    if (isSuccessful) {
                        Toast.makeText(this, "Updated successfully.",
                                Toast.LENGTH_SHORT).show();
                        this.finish();
                    } else {
                        Toast.makeText(this,
                                "Please use another login name and try again.",
                                Toast.LENGTH_SHORT).show();
                        }
                } catch (InvalidDataException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (DataAccessException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (DatabaseException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (SystemException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }     
                
                break;
            case R.id.dialog_login_register_button_cancel:
                finish();
                
            };
        }
        
        private void addRow(String editTextValue,TableLayout table, OnClickListener clickRemoveListener, int inputType)
        {
            TableRow tr = new TableRow(this); 
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));   

            // Create a TextView to house the name of the province
            EditText editText = new EditText(this);
            editText.setText(editTextValue);
            editText.setSingleLine();
            editText.setBackgroundDrawable(getResources().getDrawable(drawable.editbox_background));
            editText.setTextColor(Color.BLACK);
            editText.setInputType(inputType);
            editText.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT));
            editText.setWidth(150);        
            tr.addView(editText);

            Button btRemove = new Button(this);
            btRemove.setBackgroundDrawable(getResources().getDrawable(drawable.ic_menu_delete));
            btRemove.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT));
            tr.addView(btRemove);            
            btRemove.setOnClickListener(clickRemoveListener);

            // Add the TableRow to the TableLayout
            table.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));                      
        }              
              
        private OnClickListener mRemovePhoneListener = new OnClickListener()
        {
            public void onClick(View v)
            {
                removeRow(tablePhone, v);
            }
        };              
              
        private OnClickListener mRemoveEmailListener = new OnClickListener()
        {
            public void onClick(View v)
            {
                removeRow(tableEmail, v);
            }
        };
        
        private void removeRow(TableLayout table, View v)
        {
            table.removeViewAt(table.indexOfChild((View)v.getParent()));
        }
                
        private ArrayList<String> getAllValueInTable(TableLayout table)
        {
            ArrayList<String> results = new ArrayList<String>();
            results.clear();
            for(int i=0; i< table.getChildCount();i++)
               results.add(((EditText)((TableRow)table.getChildAt(i)).getChildAt(0)).getText().toString());  

            return results;
        }
        

}
