import * as E from 'fp-ts/Either'
import * as TE from 'fp-ts/lib/TaskEither';
import { pipe } from 'fp-ts/lib/function';

interface PostParameters{
  url: string,
  data: any
}

export async function fetchPost<Type>(parameters: PostParameters): Promise<E.Either<InternalApiError,Type>>{
  const apiResponse: TE.TaskEither<InternalApiError, Response> = TE.tryCatch(
    () => fetchPostImpl(parameters.url, parameters.data), 
    (reason: any) => ({ error: null, code: 404, reason: reason})
  );

  return await pipe(
    apiResponse, 
    TE.flatMap((response:Response) => operationResult<Type>(response)),
    TE.map(
      (result: Type) =>{
        console.log(result) 
        return result
      }
    ),
    TE.mapLeft( (error: InternalApiError) =>{
        console.error(error, "Error at Api Request") 
        return error
      }
    )
  )();
}

const fetchPostImpl = (url: string, data: any) => fetch(
    url, 
    {
      method: "POST",
      mode: "no-cors",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    }
  );

const getError = (response: Response): E.Either<InternalApiError, never> | null  => 
  (response.status == 200) ?
    null
  : E.left({
      error: jsonToErrorInformation(response.json) ,
      code: response.status,
      reason: response.statusText
    } as InternalApiError)

const jsonToErrorInformation = (json: any): ErrorInformation | null => 
  ((json as ErrorInformation).message != null) ? (json as ErrorInformation): null

const operationResult = <Type>(response: Response): TE.TaskEither<InternalApiError, Type> => 
  async () => getError(response) || E.right(await response.json() as Type);
