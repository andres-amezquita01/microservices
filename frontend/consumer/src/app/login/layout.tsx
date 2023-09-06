import styles from "./login.module.css";
import LogoLogin from "./LogoLogin";

function LoginLayout({ children }: { children: React.ReactNode }) {
  return (
    <body>
      <section className={styles.loginContainer}>
        <LogoLogin width={300} height={300} />
        {children}
      </section >
    </body>
  );
}

export default LoginLayout;
