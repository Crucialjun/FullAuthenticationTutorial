package com.crucialtech.fullauthenticationtutorial

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.crucialtech.fullauthenticationtutorial.databinding.ActivityVerifyPhoneBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class VerifyPhone : AppCompatActivity() {

    private lateinit var binding : ActivityVerifyPhoneBinding

    private var otpValid : Boolean = false

    private lateinit var otpToken : PhoneAuthProvider.ForceResendingToken
    private lateinit var verificationId : String
    private lateinit var mCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyPhoneBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        validateData(binding.otpNumberOne)
        validateData(binding.optNumberTwo)
        validateData(binding.otpNumberThree)
        validateData(binding.otpNumberFour)
        validateData(binding.otpNumberFive)
        validateData(binding.otpNumberSix)



        mCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
                binding.resendOTP.visibility = View.VISIBLE
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)

                verificationId = p0
                otpToken = p1

                binding.resendOTP.visibility = View.GONE

            }

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                TODO("Not yet implemented")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(this@VerifyPhone, "OTP Verification Failed", Toast.LENGTH_SHORT).show()
            }

        }

        if(otpValid){

        }

    }

    fun sendOtp(phoneNumber : String){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,60,TimeUnit.SECONDS,this,mCallBacks)
    }

    private fun validateData(field : EditText){
        if (field.text.isNullOrEmpty()){
            otpValid = false
            field.error = "Required Field"
        }else{
            otpValid = true
        }
    }
}