import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {

    private val _email = MutableLiveData<String>().apply {
        value = ""
    }

    val text: LiveData<String> = _email
}