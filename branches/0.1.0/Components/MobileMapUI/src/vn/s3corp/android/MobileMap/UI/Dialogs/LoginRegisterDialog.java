/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.UI.Dialogs;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.User;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.InvalidDataException;
import vn.s3corp.android.MobileMap.UI.MobileMapApp;
import vn.s3corp.android.MobileMap.UI.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The Dialog which is used to display information when users login or register.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class LoginRegisterDialog extends Dialog implements View.OnClickListener {

    private final boolean mIsRegisterring;
    private final Context mContext;

    /**
     * The constructor.
     * 
     * @param context
     *            The context which this dialog will work
     * @param isRegisterring
     *            true if this dialog needs to be opened with register
     *            information, false otherwise
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public LoginRegisterDialog(final Context context, final boolean isRegisterring) {
        super(context);

        this.mContext = context;
        this.mIsRegisterring = isRegisterring;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout.
        setContentView(R.layout.dialog_login_register);

        // Set text labels.
        TextView textLoginname = (TextView) findViewById(R.id.dialog_login_register_label_loginname);
        textLoginname.setText(R.string.label_loginname);

        TextView textPassword = (TextView) findViewById(R.id.dialog_login_register_label_password);
        textPassword.setText(R.string.label_password);

        Button btnCancel = (Button) findViewById(R.id.dialog_login_register_button_cancel);
        btnCancel.setText(R.string.label_cancel);

        Button btnOk = (Button) findViewById(R.id.dialog_login_register_button_ok);
        TextView textPasswordConfirm = (TextView) findViewById(R.id.dialog_login_register_label_password_confirm);
        EditText editPasswordConfirm = (EditText) findViewById(R.id.dialog_login_register_edit_password_confirm);

        if (this.mIsRegisterring) {
            setTitle(R.string.dialog_title_register);

            btnOk.setText(R.string.label_register);
        } else {
            setTitle(R.string.dialog_title_login);

            btnOk.setText(R.string.label_login);

            textPasswordConfirm.setVisibility(View.GONE);
            editPasswordConfirm.setVisibility(View.GONE);
        }

        // Set click events.
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    /**
     * The event handler of click events.
     * 
     * @param v
     *            the View object which receive click event
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.dialog_login_register_button_cancel: // Dismiss the dialog.
            this.dismiss();
            break;

        case R.id.dialog_login_register_button_ok:

            // Check input data.
            EditText editLoginname = (EditText) findViewById(R.id.dialog_login_register_edit_loginname);
            EditText editPassword = (EditText) findViewById(R.id.dialog_login_register_edit_password);

            if (editLoginname.getText().toString().equals("")) {
                Toast.makeText(this.mContext, "Login Name is empty.", Toast.LENGTH_SHORT).show();

                break;
            }

            if (editPassword.getText().toString().equals("")) {
                Toast.makeText(this.mContext, "Password is empty.", Toast.LENGTH_SHORT).show();

                break;
            }

            // Register user.
            MobileMapApp app = (MobileMapApp) this.getOwnerActivity().getApplication();

            if (true == this.mIsRegisterring) {
                EditText editPasswordConfirm = (EditText) findViewById(R.id.dialog_login_register_edit_password_confirm);

                if (!editPassword.getText().toString()
                        .equals(editPasswordConfirm.getText().toString())) {
                    Toast.makeText(this.mContext, "Confirm password is different from password.",
                            Toast.LENGTH_SHORT).show();

                    break;
                }

                User newUser = new User();
                newUser.setLoginname(editLoginname.getText().toString());
                newUser.setPassword(editPassword.getText().toString());

                try {
                    ContentManager contentManager = app.getContentManager();
                    boolean isSuccessful = contentManager.registerUser(newUser);

                    if (true == isSuccessful) {
                        Toast.makeText(this.mContext, "Registerring successfully.",
                                Toast.LENGTH_SHORT).show();
                        this.dismiss();
                    } else {
                        Toast.makeText(this.mContext,
                                "Can not register. Please use another login name and try again.",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (InvalidDataException e) {
                    Toast.makeText(this.mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    this.dismiss();
                } catch (DataAccessException e) {
                    Toast.makeText(this.mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    this.dismiss();
                } catch (DatabaseException e) {
                    Toast.makeText(this.mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    this.dismiss();
                }
            } else {
                // TODO: Implement login feature.
            }

            break;
        }
    }

}
