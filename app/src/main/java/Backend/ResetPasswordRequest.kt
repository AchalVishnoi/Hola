package Backend

data class ResetPasswordRequest(
    val email: String,
    val otp: String,
    val new_password: String
)