import { pipe } from 'fp-ts/lib/function';
import { createExternalAccount } from '../services/SignInApi';
import * as E from 'fp-ts/Either'

interface CreateGoogleAccountInfo {
  name: string,
  lastName: string | null,
  email: string 
  externalGoogleId: string
}

export async function CreateOrLoginGoogleAccount(info: CreateGoogleAccountInfo): Promise<boolean> {
  const createAccountResponse = await createExternalAccount(info.name, info.lastName, null, info.email, info.externalGoogleId, "Google")
  return pipe(
    createAccountResponse, 
    E.fold(() => true /* Change this in prod to a false*/, () => true)
  );
}
