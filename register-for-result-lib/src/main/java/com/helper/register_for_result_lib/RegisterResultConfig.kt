package com.helper.register_for_result_lib

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

open class RegisterResultConfig : Fragment() {
  private lateinit var callback: (ActivityResult) -> Unit

  private lateinit var requestPermissionLauncher: ActivityResultLauncher<Intent>
  private var intent: Intent? = null

  override fun onAttach(context: Context) {
   super.onAttach(context)
   requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    callback(it!!)
   }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
   super.onCreate(savedInstanceState)
   launchActivityForResult()
  }

  private fun launchActivityForResult() {
   requestPermissionLauncher.launch(intent)
  }

  companion object {
   private fun newInstance(): RegisterResultConfig {
    return RegisterResultConfig()
   }

   fun request(intent: Intent,
               activity: FragmentActivity,
               callback: (ActivityResult) -> Unit): RegisterResultConfig {
    var frag = findFragment(activity.supportFragmentManager)
    if (frag == null) {
     frag = newInstance()
     frag.intent = intent
     frag.callback = callback
     activity.supportFragmentManager
      .beginTransaction()
      .add(frag, RegisterResultConfig::class.java.simpleName)
      .commitAllowingStateLoss()
     return frag
    }
    frag.intent = intent
    frag.callback = callback
    frag.launchActivityForResult()
    return frag
   }

   private fun findFragment(fragmentManager: FragmentManager): RegisterResultConfig? {
    return fragmentManager.findFragmentByTag(RegisterResultConfig::class.java.simpleName) as RegisterResultConfig?
   }

  }
}