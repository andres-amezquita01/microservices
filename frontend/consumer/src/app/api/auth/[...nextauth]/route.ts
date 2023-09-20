import { CreateOrLoginGoogleAccount} from "@/core/signin/use_cases/CreateGoogleAccount";
import { CreateOrLoginFacebookAccount} from "@/core/signin/use_cases/CreateFacebookAccount";
import { CreateOrLoginAzureADAccount} from "@/core/signin/use_cases/CreateActiveDirectorAccount";
import NextAuth from "next-auth";
import GoogleProvider from "next-auth/providers/google";
import FacebookProvider from "next-auth/providers/facebook";
import AzureADProvider from "next-auth/providers/azure-ad";


const handler = NextAuth({
  providers: [
    GoogleProvider({
      clientId: process.env.GOOGLE_CLIENT_ID as string,
      clientSecret: process.env.GOOGLE_CLIENT_SECRET as string,
    }),
    FacebookProvider({
      clientId: process.env.FACEBOOK_CLIENT_ID as string,
      clientSecret: process.env.FACEBOOK_CLIENT_SECRET as string,
    }),
    AzureADProvider({
      clientId: process.env.AZURE_AD_CLIENT_ID as string,
      clientSecret: process.env.AZURE_AD_CLIENT_SECRET as string,
      tenantId: process.env.AZURE_AD_TENANT_ID as string,
    }),
  ],
  callbacks: {
    async signIn({user, account, profile, email, credentials}){

      switch(account?.provider.toLowerCase()){
        case "google": 
          return await CreateOrLoginGoogleAccount( {
            name: user.name || "Unknown",
            lastName: profile?.name || null,
            email: user.email || profile?.email || "",
            externalGoogleId:  user.id
          });
        case "facebook":
          return await CreateOrLoginFacebookAccount({
            name: user.name || "Unknown",
            lastName: profile?.name || null,
            email: user.email || profile?.email || "",
            externalFacebookId:  user.id
          });
        case "azure":
          return await CreateOrLoginAzureADAccount({
            name: user.name || "Unknown",
            lastName: profile?.name || null,
            email: user.email || profile?.email || "",
            externalAzureId:  user.id
          });


        default: 
          return false
      }
    }
  }
});


export { handler as GET, handler as POST };
