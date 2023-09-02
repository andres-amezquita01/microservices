package authorizations.domain.repository

import authorizations.domain.entity.PermissionContext
import roles.domain.entity.Role

trait AuthorizationRepository:
  def getPermissionContextOfUser(userId: String): PermissionContext 
  def getRolesOfUser(userId: String): List[Role]
