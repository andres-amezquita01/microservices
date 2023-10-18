package traces.domain.entity

sealed abstract class SystemAction(
  val userName: String,
  val userId: String,
  val actionId: String
)

object SystemAction {
  case class Login(
    override val userName: String,
    override val userId: String
  ) extends SystemAction(
    userName,
    userId,
    actionId = "Login"
  );

  case class SignUp(
    override val userName: String, 
    override val userId: String
  ) extends SystemAction(
    userName,
    userId,
    actionId = "SignUp"
  );

  case class DataModification(
    override val userName: String, 
    override val userId: String,
    val newDataJson: String,
    val modificatedEntity: String,
  ) extends SystemAction(
    userName, 
    userId,
    actionId = "DataModification"
  );
}
