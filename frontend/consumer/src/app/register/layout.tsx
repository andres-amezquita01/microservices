import styles from "./login.module.css";

function LoginLayout({ children }: { children: React.ReactNode }) {
  return (
    <div datatype="LoginLayaout">
      <section className={styles.loginContainer}>{children}</section>
    </div>
  );
}

export default LoginLayout;
