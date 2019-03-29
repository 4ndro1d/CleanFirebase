package android.firebase.feature.list.domain.usecase

import android.firebase.feature.list.domain.model.MyList
import android.firebase.feature.list.domain.repository.ListRepository
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Completable

class UpdateListUseCase(
    private val repository: ListRepository
) : UseCase<UpdateListUseCase.Params, Completable> {

    override fun execute(param: Params): Completable =
        repository.updateList(param.list)

    class Params(
        val list: MyList
    )
}
