package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	
	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mPrevButton;
	private ImageButton mNextButton;
	private TextView mQuestionTextView;   
	
	private TrueFalse[ ] mQuestionBank = new TrueFalse[ ] {
			new TrueFalse(R.string.question_oceans, true) ,
			new TrueFalse(R.string.question_mideast, false) ,
			new TrueFalse(R.string.question_africa, false) ,
			new TrueFalse(R.string.question_americas, true) ,
			new TrueFalse(R.string.question_asia, true) ,
	} ;
	
	private int mCurrentIndex = 0;
   
	private void updateQuestion() {
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressedTrue) {
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		
		int messageResId = 0;
		
		if (userPressedTrue == answerIsTrue) {
			messageResId= R.string.correct_toast;
		} else {
			messageResId = R.string.incorrect_toast;
		}
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
               .show( );
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view) ;
       /*  int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question); */
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {        //��ʼ��P41��ΪTextView���Ӽ�����
        	@Override
        	public void onClick(View v) {
        		mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        		updateQuestion();
        	}
        });                                                                                                              //������

        mTrueButton=(Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener( ) {
        	@Override 
        	    public void onClick(View v) {
                 /*   Toast.makeText(QuizActivity.this,
                    		                 R.string.incorrect_toast,
                    		                 Toast.LENGTH_SHORT ).show( );     */
        		checkAnswer(true);
        	}
        });
        mFalseButton=(Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener( ) {
        	@Override 
        	    public void onClick(View v) {
        		/*  Toast.makeText(QuizActivity.this,
		                 R.string.correct_toast,
		                 Toast.LENGTH_SHORT ).show( );   */
        		checkAnswer(false);
        	}
        });

        mPrevButton= (ImageButton)findViewById(R.id.prev_button);                   //��ʼ��P42����ϰ2�����Ӻ��˰�ť
        mPrevButton.setOnClickListener(new View.OnClickListener() {   
        	@Override
        	public void onClick(View v) {
        		if (mCurrentIndex%mQuestionBank.length == 0) {
        			mCurrentIndex = mQuestionBank.length - 1	;
        		} else {
        		mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
        		}
        		updateQuestion();
        	}
        });                                                                                                       //������
       
        mNextButton= (ImageButton)findViewById(R.id.next_button);  //����Next��ť��Ȼ��Ϊ�����ü�����View.OnClick.Listener
        mNextButton.setOnClickListener(new View.OnClickListener() {   //�ü����������ǣ�����������������Ӧ������ʾTextView���ı����ݡ�
        	@Override
        	public void onClick(View v) {
        		mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        		/* int question = mQuestionBank[mCurrentIndex].getQuestion();
        		mQuestionTextView.setText(question); */
        		updateQuestion();
        	}
        });
        updateQuestion();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }

}