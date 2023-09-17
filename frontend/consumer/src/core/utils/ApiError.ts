interface InternalApiError{
  error: ErrorInformation | null,
  code: number,
  reason: any | null
}

interface ErrorInformation {
  error: string,
  message: string,
  detail: string | null,
  rawError: string | null
}
