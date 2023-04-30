package com.udacity.project4.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment : Fragment() {


protected abstract val _viewModel: BaseViewModel

    override fun onStart() {
        super.onStart()
        observeShowErrorMessage()
        observeShowToast()
        observeShowSnackBar()
        observeShowSnackBarInt()
        observeNavigationCommand()
    }

    private fun observeShowErrorMessage() {
        _viewModel.showErrorMessage.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun observeShowToast() {
        _viewModel.showToast.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun observeShowSnackBar() {
        _viewModel.showSnackBar.observe(this, Observer {
            Snackbar.make(this.view!!, it, Snackbar.LENGTH_LONG).show()
        })
    }

    private fun observeShowSnackBarInt() {
        _viewModel.showSnackBarInt.observe(this, Observer {
            Snackbar.make(this.view!!, getString(it), Snackbar.LENGTH_LONG).show()
        })
    }


    private fun observeNavigationCommand() {
        _viewModel.navigationCommand.observe(this, Observer { command ->
            handleNavigationCommand(command)
        })
    }

    private fun handleNavigationCommand(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To -> navigateTo(command.directions)
            is NavigationCommand.Back -> navigateBack()
            is NavigationCommand.BackTo -> navigateBackTo(command.destinationId)
        }
    }

    private fun navigateTo(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }

    private fun navigateBackTo(destinationId: Int) {
        findNavController().popBackStack(destinationId, false)
    }


}
