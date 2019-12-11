package com.example.android.codelabs.lifecycles

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

//class SavedStateViewModelFactory(private val mState: SavedStateHandle) :
//    ViewModelProvider.NewInstanceFactory() {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return SavedStateViewModel(mState) as T
//    }
//
//}

class SavedStateViewModel(private val mState: SavedStateHandle) : ViewModel() {

    fun getName(): LiveData<String> {
        // getLiveData obtains an object that is associated with the key wrapped in a LiveData
        // so it can be observed for changes.
        return mState.getLiveData(NAME_KEY)
    }

    fun setName(newName: String) {
        // Sets a new value for the object associated to the key. There's no need to set it
        // as a LiveData.
        mState.set(NAME_KEY, newName)
    }

    companion object {
        const val NAME_KEY = "name"
    }
}
