package shared.responses

case class PaginatedResponse[A](
  data: List[A],
  meta: Meta
)
  
case class Meta (
    currentPage: Int,
    lastPage: Long,
    from: Int,
    to: Long,
    total: Long
) 
