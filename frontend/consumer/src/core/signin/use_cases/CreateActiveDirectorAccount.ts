import { pipe } from 'fp-ts/lib/function';
import { createExternalAccount } from '../services/SignInApi';
import * as E from 'fp-ts/Either'

interface CreateAzureADAccountInfo {
    name: string,
    lastName: string | null,
    email: string
    externalAzureId: string
}

export async function CreateOrLoginAzureADAccount(info: CreateAzureADAccountInfo): Promise<boolean> {
    const createAccountResponse = await createExternalAccount(info.name, info.lastName, null, info.email, info.externalAzureId, "Azure")
    return pipe(
        createAccountResponse,
        E.fold(() => true /* Change this in prod to a false*/, () => true)
    );
}
