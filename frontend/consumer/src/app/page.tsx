import LoginLayout from "./login/layout"
import LoginPage from "./login/page"
import PrincipalLayout from "./principal/layout"
import PrincipalPage from "./principal/page"

function Home() {
  return <PrincipalLayout>
    <PrincipalPage />
  </PrincipalLayout>
}

export default Home