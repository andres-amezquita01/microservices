import { pipe } from 'fp-ts/lib/function';
import { createExternalAccount } from '../services/SignInApi';
import * as E from 'fp-ts/Either'

interface CreateFacebookAccountInfo {
    name: string,
    lastName: string | null,
    email: string
    externalFacebookId: string
}

export async function CreateOrLoginFacebookAccount(info: CreateFacebookAccountInfo): Promise<boolean> {
    const createAccountResponse = await createExternalAccount(info.name, info.lastName, null, info.email, info.externalFacebookId, "Facebook");
    return pipe(
        createAccountResponse,
        E.fold(() => true /* Change this in prod to a false*/, () => true)
    );
}
