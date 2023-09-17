import * as E from 'fp-ts/Either'

interface PostParameters{
  url: string,
  data: any
}

export async function fetchPost<Type>(parameters: PostParameters): Promise<E.Either<InternalApiError,Type>>{
  const result = await fetch(
    parameters.url, 
    {
      method: "POST",
      mode: "no-cors",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(parameters.data)
    }
  );
  return getError(result) || E.right(result.json() as Type)
}

const getError = (response: Response): E.Either<InternalApiError, never> | null  => 
  (response.status == 200) ?
    null
  : E.left({
      error: jsonToErrorInformation(response.json) ,
      code: response.status
    } as InternalApiError)

const jsonToErrorInformation = (json: any): ErrorInformation | null => 
  ((json as ErrorInformation).message != null) ? (json as ErrorInformation): null
