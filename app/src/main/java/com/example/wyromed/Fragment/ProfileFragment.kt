package com.example.wyromed.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.wyromed.Activity.*
import com.example.wyromed.Activity.Interface.UserInterface
import com.example.wyromed.Activity.Presenter.UserPresenter
import com.example.wyromed.R
import com.example.wyromed.Response.Login.DataLogin
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProfileFragment : Fragment(), UserInterface {

    var fullNameTextView: TextView? = null
    var emailTextView: TextView? = null
    var user: DataLogin? = DataLogin()
    var tokenType: String? = null
    var token: String? = null
    var message: String? = null
    var menuSetting: ConstraintLayout? = null
    var menuHistory: ConstraintLayout? = null

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

        initActionButton()

        return p
    }

    private fun initActionButton() {
        menuSetting!!.onClick { startActivity<SettingActivity>(
            SettingActivity.TAGS.TOKENTYPE to tokenType,
            SettingActivity.TAGS.TOKEN to token,
            SettingActivity.TAGS.MESSAGE to message)
       }

        menuHistory!!.onClick { startActivity<HistoryTransactionsActivity>(
            HistoryTransactionsActivity.TAGS.TOKENTYPE to tokenType,
            HistoryTransactionsActivity.TAGS.TOKEN to token,
            HistoryTransactionsActivity.TAGS.MESSAGE to message)
        }
    }

    fun getUser() {
        tokenType = arguments?.getString("token_type")
        token = arguments?.getString("token")

        if(tokenType==null || token==null){
            activity?.toast("Gagal mengambil data")?.show()
            activity?.finish()
        } else {
            UserPresenter(this@ProfileFragment).getUser(tokenType, token)
        }
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