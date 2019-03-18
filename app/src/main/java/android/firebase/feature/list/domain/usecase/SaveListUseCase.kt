package android.firebase.feature.list.domain.usecase

import android.firebase.feature.list.domain.model.MyList
import android.firebase.feature.list.domain.repository.ListRepository
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Completable

class SaveListUseCase(
    val repository: ListRepository
) : UseCase<SaveListUseCase.Params, Completable> {

    override fun execute(param: Params): Completable =
        repository.saveList(param.list)

    data class Params(
        val list: MyList
    )
}
