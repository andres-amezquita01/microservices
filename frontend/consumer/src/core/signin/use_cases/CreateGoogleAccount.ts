import { createExternalAccount } from '../services/SignInApi';

interface CreateGoogleAccountInfo {
  name: string,
  lastName: string | null,
  email: string 
  externalGoogleId: string
}

export async function CreateOrLoginGoogleAccount(info: CreateGoogleAccountInfo): Promise<boolean> {
  const e = await createExternalAccount(info.name, info.lastName, null, info.email, info.externalGoogleId, "Google")
  return false; 
}

