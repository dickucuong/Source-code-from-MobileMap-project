/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author hieu.ha
 * @version 0.4.0
 * 
 */
package vn.s3corp.android.MobileMap.UI.Activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Comment;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.LoginSession;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.InvalidDataException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.SystemException;
import vn.s3corp.android.MobileMap.UI.MobileMapApp;
import vn.s3corp.android.MobileMap.UI.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ReviewsActivity extends Activity implements OnClickListener {

	private ArrayList<HashMap<String, Object>> myFeatures;
	private static final String COMMENT = "comment";
	private static final String POSTED_DATE = "posted_date";
	private static final String POSTED_BY = "posted_by";
	private ListView mListView;
	private SimpleAdapter mAdapter;
	private Intent intent;
	public static final int REQUEST_CODE = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handleLoadListView();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_post:
			EditText editTextComment = (EditText) findViewById(R.id.edittext_comment);
			MobileMapApp app = (MobileMapApp) this.getApplicationContext();
			LoginSession loginsession = app.getSession();
			if (app.checkLoginStatus() && null != loginsession) {
				if (editTextComment.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),
							R.string.message_for_empty_comment,
							Toast.LENGTH_SHORT).show();
					break;
				}
				try {
					long poiId = this.intent.getExtras().getLong("poiId");
					Comment newComment = new Comment();

					newComment.setAddressString(editTextComment.getText()
							.toString());
					newComment.setCreationDate(new Date());
					newComment.setCreationUser(loginsession.getUser());
					newComment.setActivated(true);
					newComment.setBadReport(false);

					ContentManager contentManager = app.getContentManager();
					boolean isSuccessful = contentManager.addCommet(newComment, poiId);
					if (isSuccessful) {
						Toast.makeText(this.getApplicationContext(), R.string.message_add_comment_successfully, Toast.LENGTH_SHORT).show();
						handleLoadListView();
					} else {
						Log.i("Add comment", "Adding comment");
						Toast.makeText(this.getApplicationContext(), R.string.message_add_comment_failed, Toast.LENGTH_SHORT).show();
					}

				} catch (InvalidDataException e) {
					e.printStackTrace();
				} catch (DataAccessException e) {
					e.printStackTrace();
				} catch (DatabaseException e) {
					e.printStackTrace();
				} catch (SystemException e) {
					e.printStackTrace();
				}

			} else {
				Toast.makeText(getApplicationContext(),R.string.message_for_adding_comment, Toast.LENGTH_LONG).show();
			}
			break;

		}
	}
	
	public void handleLoadListView(){
		setContentView(R.layout.activity_tab_review_comment_list);
		MobileMapApp app = (MobileMapApp) this.getApplicationContext();
		try {
			intent = getIntent();
			long poiId = intent.getExtras().getLong("poiId");
			mListView = (ListView) findViewById(android.R.id.list);
			myFeatures = new ArrayList<HashMap<String, Object>>();

			ContentManager contentManager = app.getContentManager();
			
			ArrayList<Comment> comments = contentManager.getAllPoiComments(poiId);
			for (Comment comment : comments) {
				HashMap<String, Object> hm = new HashMap<String, Object>();
				hm.put(COMMENT, comment.getCommentString());
				hm.put(POSTED_BY, comment.getCreationUser().getLoginname());
				hm.put(POSTED_DATE, comment.getCreationDate());
				myFeatures.add(hm);
			}

			mAdapter = new SimpleAdapter(this, myFeatures,
					R.layout.layout_listview_tab_review_comment, new String[] {
							COMMENT, POSTED_BY, POSTED_DATE }, new int[] {
							R.id.comment, R.id.posted_by, R.id.posted_date });
			mListView.setAdapter(mAdapter);
			mListView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					view.setBackgroundColor(R.color.white);
					if (position % 2 == 0) {
						view.setBackgroundColor(R.color.orange);
					} else {
						view.setBackgroundColor(R.color.white);
					}
				}
			});

			View buttonPost = findViewById(R.id.button_post);
			buttonPost.setOnClickListener(this);

		} catch (DataAccessException ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		} catch (DatabaseException ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		} catch (SystemException ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}
}