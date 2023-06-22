import com.example.acronyms.model.LfItemDto

data class Lf(
    val freq: Int,
    val lf: String,
    val since: Int,
    val vars: List<LfItemDto>
)