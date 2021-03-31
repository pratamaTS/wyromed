package com.example.wyromed.Fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.wyromed.Activity.*
import com.example.wyromed.Activity.Interface.UserInterface
import com.example.wyromed.Activity.Presenter.UserPresenter
import com.example.wyromed.Data.Connection.SessionManager
import com.example.wyromed.R
import com.example.wyromed.Response.Login.DataLogin
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProfileFragment : Fragment(), UserInterface {

    var fullNameTextView: TextView? = null
    var emailTextView: TextView? = null
    var user: DataLogin? = DataLogin()
    var message: String? = null
    var menuSetting: ConstraintLayout? = null
    var menuHistory: ConstraintLayout? = null
    var menuHelpSupport: ConstraintLayout? = null
    var menuSignOut: ConstraintLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Get User
        getUser()

        // Inflate the layout for this fragment
        val p = inflater.inflate(R.layout.fragment_profile, container, false)

        //INIT VIEW
        fullNameTextView = p.findViewById(R.id.tv_set_username)
        emailTextView = p.findViewById(R.id.tv_set_email)
        menuSetting = p.findViewById(R.id.menu_settings)
        menuHistory = p.findViewById(R.id.menu_history_transactions)
        menuHelpSupport = p.findViewById(R.id.menu_help_support)
        menuSignOut = p.findViewById(R.id.menu_sign_out)

        initActionButton()

        return p
    }

    private fun initActionButton() {
        menuSetting!!.onClick { startActivity<SettingActivity>(
            SettingActivity.TAGS.MESSAGE to message)
       }

        menuHistory!!.onClick { startActivity<HistoryTransactionsActivity>(
            HistoryTransactionsActivity.TAGS.MESSAGE to message)
        }
        menuSignOut!!.onClick {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Are you sure want to sign out ?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    // Delete selected note from database
                    val sessionManager = SessionManager(requireContext())
                    sessionManager.deleteAuthToken()
                    startActivity<OnBoardingActivity>(
                        OnBoardingActivity.TAGS.MESSAGE to message)
                    activity?.finish()
                }
                .setNegativeButton("No") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }
    }

    fun getUser() {
        UserPresenter(this@ProfileFragment).getUser(requireContext())
    }

    override fun onSuccessUser(dataLogin: DataLogin?) {
        //Set Value
        user = dataLogin as DataLogin

        //Set View
        fullNameTextView?.text = user?.fullname
        emailTextView?.text = user?.email
    }

    override fun onErrorUser(msg: String?) {
        toast(msg ?: "").show()
    }
}