import { CreateOrLoginGoogleAccount} from "@/core/signin/use_cases/CreateGoogleAccount";
import NextAuth from "next-auth";
import GoogleProvider from "next-auth/providers/google";

const handler = NextAuth({
  providers: [
    GoogleProvider({
      clientId: process.env.GOOGLE_CLIENT_ID as string,
      clientSecret: process.env.GOOGLE_CLIENT_SECRET as string,
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

        default: 
          return true // Change this to work with proper backend
      }
    }
  }
});


export { handler as GET, handler as POST };
