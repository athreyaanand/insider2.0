package com.example.athreyaanand.insider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity{

    public String mTmpUserToken;
    public String mTmpApiToken;
    public Boolean isRegistering;
    Boolean isShowingForm = false;

    LockableScrollView backgroundContainer;
    LoginBackgroundView backgroundView;
    Button newGameButton;
    Button showLoginButton;
    ScrollView scrollView;
    LinearLayout formWrapper;
    Button backButton;
    ImageView logoView;

    Button mLoginNormalBtn;
    ProgressBar mProgressBar;
    EditText mUsernameET;
    EditText mPasswordET;
    EditText mEmail;
    EditText mConfirmPassword;
    TextView mForgotPWTV;

    @Override
    protected int getLayoutResId() {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        return R.layout.activity_login;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        initializeElements();
        setupFacebookLogin();

        mLoginNormalBtn.setOnClickListener(mLoginNormalClick);

        mForgotPWTV.setOnClickListener(mForgotPWClick);
        SpannableString content = new SpannableString(mForgotPWTV.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        mForgotPWTV.setText(content);

        this.isRegistering = true;

        backgroundContainer.post(() -> backgroundContainer.scrollTo(0, backgroundContainer.getBottom()));
        backgroundContainer.setScrollingEnabled(false);

        newGameButton.setOnClickListener(view -> {
                isRegistering = true;
                showForm();
                setRegistering();
        });

        showLoginButton.setOnClickListener(view -> {
                isRegistering = false;
                showForm();
                setRegistering();
        });

        backButton.setOnClickListener(view ->  {
                if (isShowingForm) {
                    hideForm();
                }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.black_20_alpha));
            }
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void initializeElements(){
        backgroundContainer = (LockableScrollView) findViewById(R.id.background_container);
        backgroundView = (LoginBackgroundView) findViewById(R.id.background_view);
        newGameButton = (Button) findViewById(R.id.new_game_button);
        showLoginButton = (Button) findViewById(R.id.show_login_button);
        scrollView = (ScrollView) findViewById(R.id.login_scrollview);
        formWrapper = (LinearLayout) findViewById(R.id.login_linear_layout);
        backButton = (Button) findViewById(R.id.back_button);
        logoView = (ImageView) findViewById(R.id.logo_view);

        mLoginNormalBtn = (Button) findViewById(R.id.login_btn);
        mProgressBar = (ProgressBar) findViewById(R.id.PB_AsyncTask);
        mUsernameET = (EditText) findViewById(R.id.username);
        mPasswordET = (EditText) findViewById(R.id.password);
        mEmail = (EditText) findViewById(R.id.email);
        mConfirmPassword = (EditText) findViewById(R.id.confirm_password);
        mForgotPWTV = (TextView) findViewById(R.id.forgot_pw_tv);
    }

    @OnClick(R.id.fb_login_button)
    public void handleFacebookLogin() {
        //TODO: handle fb login
    }

    @OnClick(R.id.google_login_button)
    public void handleGoogleLogin() {
        //TODO: handle google login
    }
    private void setupFacebookLogin() {
        //TODO: setup facebook login
    }

    @Override
    public void onBackPressed() {
        if (isShowingForm) {
            hideForm();
        } else {
            super.onBackPressed();
        }
    }

    private View.OnClickListener mLoginNormalClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mProgressBar.setVisibility(View.VISIBLE);
            if (isRegistering) {
                String username, email, password, cpassword;
                username = String.valueOf(mUsernameET.getText()).trim();
                email = String.valueOf(mEmail.getText()).trim();
                password = String.valueOf(mPasswordET.getText());
                cpassword = String.valueOf(mConfirmPassword.getText());
                if (username.length() == 0 || password.length() == 0 || email.length() == 0 || cpassword.length() == 0) {
                    showValidationError(R.string.login_validation_error_fieldsmissing);
                    return;
                }
            } else {
                String username, password;
                username = String.valueOf(mUsernameET.getText()).trim();
                password = String.valueOf(mPasswordET.getText());
                if (username.length() == 0 || password.length() == 0) {
                    showValidationError(R.string.login_validation_error_fieldsmissing);
                    return;
                }
            }
        }
    };

    private View.OnClickListener mForgotPWClick = v -> {
        //TODO: Forgot Password
        showSnackbar("Forgot Password Clicked!");
    };


    public static void show(final View v) {
        v.setVisibility(View.VISIBLE);
    }

    public static void hide(final View v) {
        v.setVisibility(View.GONE);
    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void startSetupActivity() {
        //TODO: create setup activity for new accounts
        /*Intent intent = new Intent(LoginActivity.this, SetupActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();*/
    }

    private void toggleRegistering() {
        this.isRegistering = !this.isRegistering;
        this.setRegistering();
    }

    private void setRegistering() {
        if (this.isRegistering) {
            this.mLoginNormalBtn.setText(getString(R.string.register_btn));
            mUsernameET.setHint(R.string.username);
            mPasswordET.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        } else {
            this.mLoginNormalBtn.setText(getString(R.string.login_btn));
            mUsernameET.setHint(R.string.email_username);
            mPasswordET.setImeOptions(EditorInfo.IME_ACTION_DONE);
        }
    }

    private void showSnackbar(String content) {
        Snackbar snackbar = Snackbar
                .make(this.findViewById(R.id.login_linear_layout), content, Snackbar.LENGTH_LONG);

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.RED);//change Snackbar's background color;
        snackbar.show(); // Don’t forget to show!
    }

    private void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void showValidationError(int resourceMessageString) {
        //TODO: update dialog icon
        mProgressBar.setVisibility(View.GONE);
        new android.support.v7.app.AlertDialog.Builder(this)
                .setTitle(R.string.login_validation_error_title)
                .setMessage(resourceMessageString)
                .setNeutralButton(android.R.string.ok, (dialog, which) -> {
                })
                .setIcon(R.drawable.ic_launcher)
                .show();
    }


    private void showForm() {
        isShowingForm = true;
        ValueAnimator panAnimation = ObjectAnimator.ofInt(backgroundContainer, "scrollY", 0).setDuration(1000);
        ValueAnimator newGameAlphaAnimation = ObjectAnimator.ofFloat(newGameButton, View.ALPHA, 0);
        ValueAnimator showLoginAlphaAnimation = ObjectAnimator.ofFloat(showLoginButton, View.ALPHA, 0);
        ValueAnimator scaleLogoAnimation = ValueAnimator.ofInt(logoView.getMeasuredHeight(), (int)(logoView.getMeasuredHeight()*0.75));
        scaleLogoAnimation.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = logoView.getLayoutParams();
            layoutParams.height = val;
            logoView.setLayoutParams(layoutParams);
        });
        if (isRegistering) {
            newGameAlphaAnimation.setStartDelay(600);
            newGameAlphaAnimation.setDuration(400);
            showLoginAlphaAnimation.setDuration(400);
            newGameAlphaAnimation.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    newGameButton.setVisibility(View.GONE);
                    showLoginButton.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                    scrollView.setAlpha(1);
                }
            });
        } else {
            showLoginAlphaAnimation.setStartDelay(600);
            showLoginAlphaAnimation.setDuration(400);
            newGameAlphaAnimation.setDuration(400);
            showLoginAlphaAnimation.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    newGameButton.setVisibility(View.GONE);
                    showLoginButton.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                    scrollView.setAlpha(1);
                }
            });
        }
        ValueAnimator backAlphaAnimation = ObjectAnimator.ofFloat(backButton, View.ALPHA, 1).setDuration(800);
        AnimatorSet showAnimation = new AnimatorSet();
        showAnimation.playTogether(panAnimation, newGameAlphaAnimation, showLoginAlphaAnimation, scaleLogoAnimation);
        showAnimation.play(backAlphaAnimation).after(panAnimation);
        for (int i = 0; i < formWrapper.getChildCount(); i++) {
            View view = formWrapper.getChildAt(i);
            view.setAlpha(0);
            ValueAnimator animator = ObjectAnimator.ofFloat(view, View.ALPHA, 1).setDuration(400);
            animator.setStartDelay(100 * i);
            showAnimation.play(animator).after(panAnimation);
        }

        showAnimation.start();
    }

    private void hideForm() {
        isShowingForm = false;
        ValueAnimator panAnimation = ObjectAnimator.ofInt(backgroundContainer, "scrollY", backgroundContainer.getBottom()).setDuration(1000);
        ValueAnimator newGameAlphaAnimation = ObjectAnimator.ofFloat(newGameButton, View.ALPHA, 1).setDuration(700);
        ValueAnimator showLoginAlphaAnimation = ObjectAnimator.ofFloat(showLoginButton, View.ALPHA, 1).setDuration(700);
        ValueAnimator scaleLogoAnimation = ValueAnimator.ofInt(logoView.getMeasuredHeight(), (int)(logoView.getMeasuredHeight()*1.333333));
        scaleLogoAnimation.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = logoView.getLayoutParams();
            layoutParams.height = val;
            logoView.setLayoutParams(layoutParams);
        });
        showLoginAlphaAnimation.setStartDelay(300);
        ValueAnimator scrollViewAlphaAnimation = ObjectAnimator.ofFloat(scrollView, View.ALPHA, 0).setDuration(800);
        scrollViewAlphaAnimation.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                newGameButton.setVisibility(View.VISIBLE);
                showLoginButton.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.INVISIBLE);
            }
        });
        ValueAnimator backAlphaAnimation = ObjectAnimator.ofFloat(backButton, View.ALPHA, 0).setDuration(800);
        AnimatorSet showAnimation = new AnimatorSet();
        showAnimation.playTogether(panAnimation, scrollViewAlphaAnimation, backAlphaAnimation, scaleLogoAnimation);
        showAnimation.play(newGameAlphaAnimation).after(scrollViewAlphaAnimation);
        showAnimation.play(showLoginAlphaAnimation).after(scrollViewAlphaAnimation);
        showAnimation.start();
    }
}