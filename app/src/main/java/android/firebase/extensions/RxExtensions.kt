@file:Suppress("unused")

package android.firebase.extensions

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Returns [Maybe#empty] if original Maybe emits an empty list
 */
fun <E, T : List<E>> Maybe<T>.emptyMaybeForEmptyList(): Maybe<T> =
    this.flatMap { items -> if (items.isEmpty()) Maybe.empty() else Maybe.just(items) }

/*
 * observe on extensions
 */
fun <T> Observable<T>.observeOnIo(): Observable<T> = observeOn(Schedulers.io())

fun <T> Flowable<T>.observeOnIo(): Flowable<T> = observeOn(Schedulers.io())
fun <T> Single<T>.observeOnIo(): Single<T> = observeOn(Schedulers.io())
fun <T> Maybe<T>.observeOnIo(): Maybe<T> = observeOn(Schedulers.io())
fun Completable.observeOnIo(): Completable = observeOn(Schedulers.io())

fun <T> Observable<T>.observeOnComputation(): Observable<T> = observeOn(Schedulers.computation())
fun <T> Flowable<T>.observeOnComputation(): Flowable<T> = observeOn(Schedulers.computation())
fun <T> Single<T>.observeOnComputation(): Single<T> = observeOn(Schedulers.computation())
fun <T> Maybe<T>.observeOnComputation(): Maybe<T> = observeOn(Schedulers.computation())
fun Completable.observeOnComputation(): Completable = observeOn(Schedulers.computation())

fun <T> Observable<T>.observeOnMain(): Observable<T> = observeOn(AndroidSchedulers.mainThread())
fun <T> Flowable<T>.observeOnMain(): Flowable<T> = observeOn(AndroidSchedulers.mainThread())
fun <T> Single<T>.observeOnMain(): Single<T> = observeOn(AndroidSchedulers.mainThread())
fun <T> Maybe<T>.observeOnMain(): Maybe<T> = observeOn(AndroidSchedulers.mainThread())
fun Completable.observeOnMain(): Completable = observeOn(AndroidSchedulers.mainThread())

/*
 * subscribe on extensions
 */
fun <T> Observable<T>.subscribeOnIo(): Observable<T> = subscribeOn(Schedulers.io())

fun <T> Flowable<T>.subscribeOnIo(): Flowable<T> = subscribeOn(Schedulers.io())
fun <T> Single<T>.subscribeOnIo(): Single<T> = subscribeOn(Schedulers.io())
fun <T> Maybe<T>.subscribeOnIo(): Maybe<T> = subscribeOn(Schedulers.io())
fun Completable.subscribeOnIo(): Completable = subscribeOn(Schedulers.io())

fun <T> Observable<T>.subscribeOnComputation(): Observable<T> = subscribeOn(Schedulers.computation())
fun <T> Flowable<T>.subscribeOnComputation(): Flowable<T> = subscribeOn(Schedulers.computation())
fun <T> Single<T>.subscribeOnComputation(): Single<T> = subscribeOn(Schedulers.computation())
fun <T> Maybe<T>.subscribeOnComputation(): Maybe<T> = subscribeOn(Schedulers.computation())
fun Completable.subscribeOnComputation(): Completable = subscribeOn(Schedulers.computation())

fun <T> Observable<T>.subscribeOnMain(): Observable<T> = subscribeOn(AndroidSchedulers.mainThread())
fun <T> Flowable<T>.subscribeOnMain(): Flowable<T> = subscribeOn(AndroidSchedulers.mainThread())
fun <T> Single<T>.subscribeOnMain(): Single<T> = subscribeOn(AndroidSchedulers.mainThread())
fun <T> Maybe<T>.subscribeOnMain(): Maybe<T> = subscribeOn(AndroidSchedulers.mainThread())
fun Completable.subscribeOnMain(): Completable = subscribeOn(AndroidSchedulers.mainThread())
