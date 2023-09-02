package shared.requests

class PaginatedRequest (
  page:Int,
  perPage:Int
) {
  lazy val from = perPage * page
  lazy val to = from + perPage

  def getTo(total: Long) = if(total < to) total else (to.toLong)

  def getLastPage(total: Long): Long =
    ((total / perPage).max(page.toLong) - 1).max(0)
}
