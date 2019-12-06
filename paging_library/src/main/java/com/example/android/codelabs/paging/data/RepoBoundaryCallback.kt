package com.example.android.codelabs.paging.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList.BoundaryCallback
import com.example.android.codelabs.paging.api.GithubService
import com.example.android.codelabs.paging.api.searchRepos
import com.example.android.codelabs.paging.db.GithubLocalCache
import com.example.android.codelabs.paging.model.Repo

/**
 * this class handles the network requests and database data saving for a specific query.
 */
class RepoBoundaryCallback(
    private val query: String,
    private val service: GithubService,
    private val cache: GithubLocalCache
) : BoundaryCallback<Repo>() {

    // keep the last requested page.
    // When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    //internally, in the RepoBoundaryCallback class, we can work with a MutableLiveData,
    // but outside the class, we only expose a LiveData object, whose values can't be modified.
    private val _networkErrors = MutableLiveData<String>()

    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSaveData(query)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Repo) {
        requestAndSaveData(query)
    }

    private fun requestAndSaveData(query: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        searchRepos(service, query, lastRequestedPage, NETWORK_PAGE_SIZE, { repos ->
            cache.insert(repos) {
                lastRequestedPage++
                isRequestInProgress = false
            }
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }


    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}