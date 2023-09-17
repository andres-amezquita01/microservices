interface InternalApiError{
  error: ErrorInformation | null,
  code: number
}

interface ErrorInformation {
  error: string,
  message: string,
  detail: string | null,
  rawError: string | null
}
