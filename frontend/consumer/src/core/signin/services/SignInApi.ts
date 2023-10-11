import { fetchPost } from "@/core/utils/Requests";

const API_URL = process.env.BACKEND_URL;

export async function createExternalAccount(
  name: string,
  lastName: string | null,
  phone: string | null,
  email: string,
  externalId: string,
  externalSource: string,
) {
  return await fetchPost<CreateAccountResponse>(
    {
      url: `${API_URL}/external-signup`,
      data: {
        name: name,
        lastName: lastName,
        phone: phone,
        email: email,
        externalId: externalId,
        externalSource: externalSource
      }
    }
  )
}

interface CreateAccountResponse{
  username: string,
  agentId: string,
  userId: string,
  email: string,
  status: string
}
