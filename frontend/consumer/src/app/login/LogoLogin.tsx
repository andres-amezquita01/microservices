//import styles from "./login.module.css";
import { signIn} from "next-auth/react";
import ButtomSocial from "@/components/ButtomSocial/ButtomSocial"
export default function LogoLogin({width=200, height=200}: IProps) {

  const logInWithMethod = async (method: string) => {
    signIn(method, { callbackUrl: "/home" });
  };


  return (
    <div className={"{styles.logo} lg:flex-col"}>
      <ButtomSocial logInWithMethod={logInWithMethod } />
    </div>
  );
}

interface IProps {
  width?: number;
  height?: number;

}