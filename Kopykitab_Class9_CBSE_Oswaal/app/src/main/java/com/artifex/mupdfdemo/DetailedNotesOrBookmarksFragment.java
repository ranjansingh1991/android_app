package com.artifex.mupdfdemo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import com.artifex.mupdfdemo.MuPDFActivity.UtilsPDF;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kopykitab.class9.cbse.oswaal.R;
import com.kopykitab.class9.cbse.oswaal.components.CircularProgressView;
import com.kopykitab.class9.cbse.oswaal.settings.AppSettings;
import com.kopykitab.class9.cbse.oswaal.settings.Constants;
import com.kopykitab.class9.cbse.oswaal.settings.Utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


class NotesOrBookmarksAdapterDividerItemDecoration extends RecyclerView.ItemDecoration {
	private Drawable divider;

	public NotesOrBookmarksAdapterDividerItemDecoration(Drawable divider) {
		this.divider = divider;
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent) {
		// TODO Auto-generated method stub
		super.onDraw(c, parent);
		int left = parent.getPaddingLeft();
		int right = parent.getWidth() - parent.getPaddingRight();

		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = parent.getChildAt(i);

			RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

			int top = child.getBottom() + params.bottomMargin;
			int bottom = top + divider.getIntrinsicHeight();

			divider.setBounds(left, top, right, bottom);
			divider.draw(c);
		}
	}
}

class NotesOrBookmarksAdapter extends RecyclerView.Adapter<NotesOrBookmarksAdapter.ViewHolder> {
	public class ViewHolder extends RecyclerView.ViewHolder {
		public View v;

		public ViewHolder(View v) {
			super(v);
			this.v = v;
		}
	}

	private Context mContext;
	private String customerId, productId, bookName;
	private HashMap<String, String> book;
	private int notesOrBookmarksType;
	private ViewFlipper noteOrBookmarksFlip;
	private List<HashMap<String, String>> allAnnotationData;
	private View notesOrBookmarksView;

	public NotesOrBookmarksAdapter(Context mContext, HashMap<String, String> book, int notesOrBookmarksType, ViewFlipper noteOrBookmarksFlip, List<HashMap<String, String>> allAnnotationData) {
		this.mContext = mContext;
		this.book = book;
		this.notesOrBookmarksType = notesOrBookmarksType;
		this.noteOrBookmarksFlip = noteOrBookmarksFlip;
		this.allAnnotationData = allAnnotationData;

		//customerId
		customerId = AppSettings.getInstance(mContext).get("CUSTOMER_ID");

		//product ID
		if (book != null && book.containsKey("product_id")) {
			productId = book.get("product_id");
		} else {
			productId = "-1";
		}

		//book name
		if (book != null && book.containsKey("name")) {
			bookName = book.get("name");
		} else {
			bookName = "-1";
		}
	}

	public void updateNotes(int position, HashMap<String, String> annotationData) {
		allAnnotationData.get(position).putAll(annotationData);
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return allAnnotationData.size();
	}

	@Override
	public NotesOrBookmarksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		View v = LayoutInflater.from(mContext).inflate(R.layout.notes_bookmarks_view_items, parent, false);
		ViewHolder vh = new ViewHolder(v);

		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		// TODO Auto-generated method stub
		final HashMap<String, String> annotationData = allAnnotationData.get(position);

		notesOrBookmarksView = holder.v;
		//call method for getting formatted date like : 4th Apr, 2017
		final String date_added = getFormattedDate(annotationData.get("date_added"));

		((TextView) notesOrBookmarksView.findViewById(R.id.chapter_info)).setText(annotationData.get("chapter_name"));
		((TextView) notesOrBookmarksView.findViewById(R.id.notes_date)).setText(date_added);
		((TextView) notesOrBookmarksView.findViewById(R.id.bookmark_page_no)).setText("Page No:" + annotationData.get("page_number"));
		((TextView) notesOrBookmarksView.findViewById(R.id.notes_text)).setText(annotationData.get("notes"));

		Button deleteNotesOrBookmark = (Button) notesOrBookmarksView.findViewById(R.id.delete_notes_bookmark_button);
		deleteNotesOrBookmark.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				allAnnotationData.remove(position);
				notifyDataSetChanged();

				if (allAnnotationData == null || allAnnotationData.size() <= 0) {
					if (notesOrBookmarksType == 0) {
						noteOrBookmarksFlip.setDisplayedChild(1);
					} else if (notesOrBookmarksType == 1) {
						noteOrBookmarksFlip.setDisplayedChild(2);
					}
				}

				if (notesOrBookmarksType == 0) {
					UtilsPDF.deleteNotesDataFromJSONFile(mContext, customerId, productId, (Integer.valueOf(annotationData.get("page_number")) - 1), annotationData.get("coordinate"));
				} else if (notesOrBookmarksType == 1) {
					updateBookmarksJSONFile(Integer.valueOf(annotationData.get("page_number")));
				}
			}
		});

		Button note = (Button) notesOrBookmarksView.findViewById(R.id.note_button);
		note.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				final AlertDialog alertDialog = UtilsPDF.createAlertBox(mContext);
				alertDialog.show();

				ImageButton cancelNotes = ((ImageButton) alertDialog.findViewById(R.id.cancelNotesButton));
				Button saveNotesButton = ((Button) alertDialog.findViewById(R.id.saveNotesButton));
				final EditText editText = ((EditText) alertDialog.findViewById(R.id.dialogEditText));

				editText.setText(annotationData.get("notes"));
				editText.setSelection(annotationData.get("notes").length());

				saveNotesButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String notesMsg = editText.getText().toString().trim();
						CharSequence text = "";
						if(notesMsg != null && notesMsg != "" && notesMsg.length() > 0) {
							HashMap<String, String> notesData = new HashMap<String, String>();
							notesData.put("notes", notesMsg);
							notesData.put("date_added", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime()));

							Integer pageNumber = (Integer.valueOf(annotationData.get("page_number")) - 1);
							if (notesOrBookmarksType == 0) {

								//save notes to Notes JSON file
								UtilsPDF.saveNotes(mContext, notesData, customerId, productId, pageNumber, annotationData.get("coordinate"));
							} else if (notesOrBookmarksType == 1) {

								//save notes to bookmark JSON file
								updateNotesInBookmarksJSONFile(pageNumber, notesData);
							}

							//update notes
							annotationData.put("notes", notesMsg);
							annotationData.put("date_added", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime()));

							//update data to recyclerView
							updateNotes(position, annotationData);

							//disable alert dialog
							alertDialog.dismiss();

							text = "Notes are Updated...";
						} else {
							text = "Notes can't be empty...";
						}

						//show toast
						Toast toast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
						toast.show();
					}
				});

				cancelNotes.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						alertDialog.dismiss();
					}
				});
			}
		});

		Button share = (Button) notesOrBookmarksView.findViewById(R.id.share_button);
		share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				String customerName = AppSettings.getInstance(mContext).get("customer_name");
				Integer pageNumber = (Integer.valueOf(annotationData.get("page_number")) - 1);

				String shareSubject = "Your friend " + customerName + " shared study success with you";
				String shareBody = "I am reading " + bookName;
				if (notesOrBookmarksType == 0) {
					shareBody += "\n\nI have created a note on the book";

					String coordinate = annotationData.get("coordinate");
					if(coordinate.startsWith("8.0")) {
						Utils.triggerGAEvent(mContext, "Pdf_Annotation_Share_Highlight", customerId , productId + "_" + pageNumber);
					} else if(coordinate.startsWith("9.0")) {
						Utils.triggerGAEvent(mContext, "Pdf_Annotation_Share_Underline", customerId , productId + "_" + pageNumber);
					} else if(coordinate.startsWith("11.0")) {
						Utils.triggerGAEvent(mContext, "Pdf_Annotation_Share_Strikeout", customerId , productId + "_" + pageNumber);
					}
				} else if (notesOrBookmarksType == 1) {
					shareBody += "\n\nI have found below part of the book very important";

					Utils.triggerGAEvent(mContext, "Pdf_Annotation_Share_Bookmark", customerId , productId + "_" + pageNumber);
				}

				String chapterName = annotationData.get("chapter_name");
				if(!chapterName.equals(""))
					shareBody += "\nChapter Name : " + chapterName;

				shareBody += "\nPage No : " + (pageNumber + 1);

				String note = annotationData.get("notes");
				if(!note.equals(""))
					shareBody += "\nMy Note : " + note;

				shareBody += "\n\nShared Via : " + Constants.BASE_URL;

				Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSubject);
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
				mContext.startActivity(Intent.createChooser(sharingIntent, "Share Notes OR Bookmarks Via :"));
			}
		});

		notesOrBookmarksView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((MuPDFActivity) mContext).onActivityResult(0, (Integer.valueOf(allAnnotationData.get(position).get("page_number")) - 1), null);
			}
		});
	}

	/* load bookmarked page number from JSON file and update the JSON File */
	protected void updateBookmarksJSONFile(Integer pageNumber) {
		File jsonFile = new File(Utils.getDirectory(mContext) + Constants.BOOKMARKED_PAGE_JSON_FILENAME);
		try {
			if (jsonFile.exists()) {
				HashMap<String, HashMap<Integer, HashMap<String, String>>> fileDataBookmark = new HashMap<String, HashMap<Integer, HashMap<String, String>>>();
				fileDataBookmark = new Gson().fromJson(new FileReader(jsonFile), new TypeToken<HashMap<String, HashMap<Integer, HashMap<String, String>>>>() {
				}.getType());
				if (fileDataBookmark != null && fileDataBookmark.size() > 0) {
					String mainKey = customerId + "_" + productId;
					if (fileDataBookmark.get(mainKey) != null) {
						HashMap<String, String> notesData = fileDataBookmark.get(mainKey).remove(pageNumber - 1);

						//update Bookmark icon
						MuPDFActivity.updateBookmarkPageIconWhenDelete(fileDataBookmark.get(mainKey), (pageNumber - 1));
						Utils.triggerGAEvent(mContext, "Pdf_Annotation_Delete_Bookmark", customerId , productId + "_" + (pageNumber - 1));
						String notes = notesData.get("notes");
						if(!notes.equals("")) {
							Utils.triggerGAEvent(mContext, "Pdf_Annotation_Delete_Bookmark_Notes", customerId , productId + "_" + (pageNumber - 1));
						}

						//update JSON File
						try {
							FileWriter newJsonFile = new FileWriter(new File(Utils.getDirectory(mContext) + Constants.BOOKMARKED_PAGE_JSON_FILENAME));
							newJsonFile.write(new Gson().toJson(fileDataBookmark));
							newJsonFile.flush();
							newJsonFile.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void updateNotesInBookmarksJSONFile(Integer pageNumber, HashMap<String, String> notesData) {
		String mainKey = customerId + "_" + productId;
		File jsonFile = new File(Utils.getDirectory(mContext) + Constants.BOOKMARKED_PAGE_JSON_FILENAME);
		try {
			if (jsonFile.exists()) {
				HashMap<String, HashMap<Integer, HashMap<String, String>>> fileDataBookmark = new HashMap<String, HashMap<Integer, HashMap<String, String>>>();
				fileDataBookmark = new Gson().fromJson(new FileReader(jsonFile), new TypeToken<HashMap<String, HashMap<Integer, HashMap<String, String>>>>() {
				}.getType());
				if (fileDataBookmark != null && fileDataBookmark.size() > 0) {
					HashMap<Integer, HashMap<String, String>> bookmarkedData = fileDataBookmark.get(mainKey);
					if (bookmarkedData != null) {
						bookmarkedData.put(pageNumber, notesData);
						fileDataBookmark.put(mainKey, bookmarkedData);

						Utils.triggerGAEvent(mContext, "Pdf_Annotation_Bookmark_Notes", customerId , productId + "_" + pageNumber);

						//update JSON File
						try {
							FileWriter newJsonFile = new FileWriter(new File(Utils.getDirectory(mContext) + Constants.BOOKMARKED_PAGE_JSON_FILENAME));
							newJsonFile.write(new Gson().toJson(fileDataBookmark));
							newJsonFile.flush();
							newJsonFile.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getFormattedDate(String date_added) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(date_added);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			//5th Mar, 2015
			int day = cal.get(Calendar.DATE);

			if (!((day > 10) && (day < 19)))
				switch (day % 10) {
				case 1:
					return new SimpleDateFormat("d'st' MMM',' yyyy").format(date);
				case 2:
					return new SimpleDateFormat("d'nd' MMM',' yyyy").format(date);
				case 3:
					return new SimpleDateFormat("d'rd' MMM',' yyyy").format(date);
				default:
					return new SimpleDateFormat("d'th' MMM',' yyyy").format(date);
				}
			return new SimpleDateFormat("d'th' MMM',' yyyy").format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return date_added;
	}

}

@SuppressLint("ValidFragment")
public class DetailedNotesOrBookmarksFragment extends Fragment {
	private Context mContext;
	private View rootView = null;
	private ViewFlipper noteOrBookmarksFlip;
	private RecyclerView noteOrBookmarksResults;
	private String productId;
	HashMap<String, String> book;
	private LinkedHashMap<Integer, String> mChapterInfo;
	private int position;
	private NotesOrBookmarksAdapter notesOrBookmarksAadapter;

	public DetailedNotesOrBookmarksFragment() {

	}

	public DetailedNotesOrBookmarksFragment(Context mContext, int position, LinkedHashMap<Integer, String> mChapterInfo, HashMap<String, String> book) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.position = position;
		this.mChapterInfo = mChapterInfo;
		this.book = book;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if(mContext != null) {
			rootView = LayoutInflater.from(mContext).inflate(R.layout.notes_bookmarks_view, container, false);

			noteOrBookmarksFlip = (ViewFlipper) rootView.findViewById(R.id.notes_and_bookmarks_flip);
			noteOrBookmarksFlip.setDisplayedChild(0);

			noteOrBookmarksResults = (RecyclerView) rootView.findViewById(R.id.notes_and_bookmarks_results);

			noteOrBookmarksResults.setHasFixedSize(true);

			noteOrBookmarksResults.setLayoutManager(new LinearLayoutManager(mContext));
			noteOrBookmarksResults.addItemDecoration(new NotesOrBookmarksAdapterDividerItemDecoration(ContextCompat.getDrawable(mContext, R.drawable.item_divider)));

			new GetNotesOrBookmarksByType(mContext, position).forceLoad();
		}

		return rootView;
	}

	private class GetNotesOrBookmarksByType extends AsyncTaskLoader<List<HashMap<String, String>>> {

		private int notesOrBookmarksType;
		private HashMap<String, HashMap<Integer, HashMap<String, String>>> fileDataBookmark;
		private HashMap<Integer, HashMap<String, String>> bookmarkedData;
		private List<HashMap<String, String>> allAnnotationData = new ArrayList<HashMap<String, String>>();

		public GetNotesOrBookmarksByType(Context context, int notesOrBookmarksType) {
			super(context);
			this.notesOrBookmarksType = notesOrBookmarksType;

			if (book != null && book.containsKey("product_id")) {
				productId = book.get("product_id");
			} else {
				productId = "-1";
			}
		}

		@Override
		public List<HashMap<String, String>> loadInBackground() {
			// TODO Auto-generated method stub
			if (notesOrBookmarksType == 0) {
				HashMap<String, HashMap<String, HashMap<String, String>>> fileDataNotes = new HashMap<String, HashMap<String, HashMap<String, String>>>();
				HashMap<String, HashMap<String, String>> allNotesAndCoordinates = new HashMap<String, HashMap<String, String>>();
				try {
					File jsonFile = new File(Utils.getDirectory(mContext) + Constants.ANNOT_ADD_NOTES_JSON_FILENAME);
					if (jsonFile.exists()) {
						fileDataNotes = new Gson().fromJson(new FileReader(jsonFile), new TypeToken<HashMap<String, HashMap<String, HashMap<String, String>>>>() {
						}.getType());
						if (fileDataNotes != null && fileDataNotes.size() > 0) {
							String mainKey = AppSettings.getInstance(mContext).get("CUSTOMER_ID") + "_" + productId;

							List<Integer> sortedPageNumbers = new ArrayList<Integer>();
							for (String key : fileDataNotes.keySet()) {
								if (key.startsWith(mainKey)) {
									String[] pageNumberArray = key.split("___");
									sortedPageNumbers.add(Integer.valueOf(pageNumberArray[1]));
								}
							}

							//sort page number
							Collections.sort(sortedPageNumbers);

							for (Integer pageNumber : sortedPageNumbers) {
								allNotesAndCoordinates = fileDataNotes.get(mainKey + "___" + Integer.toString(pageNumber));
								if (allNotesAndCoordinates != null && allNotesAndCoordinates.size() > 0) {
									for (String coordinate : allNotesAndCoordinates.keySet()) {
										HashMap<String, String> notesData = allNotesAndCoordinates.get(coordinate);
										HashMap<String, String> allDataWithKeys = new HashMap<String, String>();
										allDataWithKeys.put("chapter_name", getChapterNameByPageNumber(pageNumber));
										allDataWithKeys.put("page_number", Integer.toString((pageNumber.intValue() + 1)));
										allDataWithKeys.put("date_added", notesData.get("date_added"));
										allDataWithKeys.put("notes", notesData.get("notes"));
										allDataWithKeys.put("coordinate", coordinate.toString());

										allAnnotationData.add(allDataWithKeys);
									}
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (notesOrBookmarksType == 1) {
				//load bookmarked page number from JSON file
				File jsonFile = new File(Utils.getDirectory(mContext) + Constants.BOOKMARKED_PAGE_JSON_FILENAME);
				try {
					if (jsonFile.exists()) {
						fileDataBookmark = new Gson().fromJson(new FileReader(jsonFile), new TypeToken<HashMap<String, HashMap<Integer, HashMap<String, String>>>>() {
						}.getType());
						if (fileDataBookmark != null && fileDataBookmark.size() > 0) {
							String mainKey = AppSettings.getInstance(mContext).get("CUSTOMER_ID") + "_" + productId;
							if (fileDataBookmark.get(mainKey) != null && fileDataBookmark.get(mainKey).size() > 0) {
								if (bookmarkedData == null) {
									bookmarkedData = new HashMap<Integer, HashMap<String, String>>();
								}
								bookmarkedData.putAll(fileDataBookmark.get(mainKey));

								//sort a key for showing a list based on ascending order of page number
								List<Integer> sortedKeys = new ArrayList<Integer>(bookmarkedData.keySet());
								Collections.sort(sortedKeys);

								for (Integer key : sortedKeys) {
									HashMap<String, String> allDataWithKeys = new HashMap<String, String>();

									allDataWithKeys.put("chapter_name", getChapterNameByPageNumber(key));
									allDataWithKeys.put("page_number", Integer.toString((key.intValue() + 1)));
									allDataWithKeys.put("date_added", bookmarkedData.get(key).get("date_added"));
									allDataWithKeys.put("notes", bookmarkedData.get(key).get("notes"));

									allAnnotationData.add(allDataWithKeys);
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return allAnnotationData;
		}

		@Override
		public void deliverResult(final List<HashMap<String, String>> allAnnotationData) {
			// TODO Auto-generated method stub
			super.deliverResult(allAnnotationData);

			if (allAnnotationData.size() > 0) {
				notesOrBookmarksAadapter = new NotesOrBookmarksAdapter(mContext, book, notesOrBookmarksType, noteOrBookmarksFlip, allAnnotationData);
				noteOrBookmarksResults.setLayoutManager(new LinearLayoutManager(mContext));
				noteOrBookmarksResults.setAdapter(notesOrBookmarksAadapter);
			} else {
				if (notesOrBookmarksType == 0) {
					noteOrBookmarksFlip.setDisplayedChild(1);
				} else if (notesOrBookmarksType == 1) {
					noteOrBookmarksFlip.setDisplayedChild(2);
				}
			}
			CircularProgressView notesAndBookmarksProgress = (CircularProgressView) rootView.findViewById(R.id.notes_and_bookmarks_progress);
			notesAndBookmarksProgress.setVisibility(View.GONE);
		}

		private String getChapterNameByPageNumber(Integer key) {
			String chapterName = "";
			if (mChapterInfo.size() > 0) {
				if (mChapterInfo.containsKey(key)) {
					chapterName = mChapterInfo.get(key);
				} else {
					for (Integer pageKey : mChapterInfo.keySet()) {
						Integer nextPageKey = nextPageKey(pageKey);
						if (nextPageKey != null) {
							if ((pageKey.intValue() > key.intValue()) && (nextPageKey.intValue() > key.intValue())) {
								chapterName = mChapterInfo.get(pageKey);
								break;
							}

							if (nextPageKey.intValue() > key.intValue()) {
								chapterName = mChapterInfo.get(pageKey);
								break;
							}
						} else {
							chapterName = mChapterInfo.get(pageKey);
							break;
						}
					}
				}
			}

			return chapterName;
		}

		private Integer nextPageKey(Integer pageKey) {
			for (Integer nextPageKey : mChapterInfo.keySet()) {
				if (nextPageKey.intValue() > pageKey.intValue())
					return nextPageKey;
			}

			return null;
		}
	}
}
