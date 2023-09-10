'use client'
import { Form } from "@/components/Form"
import ButtomSocial from "@/components/ButtomSocialP/ButtomSocial"

function PricipalPage() {

  const PrincipalSubmit = async (formData: any) => {
    /*startLoading()
    await authRouter({
      endpoint: "login",
      formData,
      redirectRoute: "/dashboard",
    });
    finishLoading()
*/
  }

  return (
   <>
   <Form
    onSubmit={PrincipalSubmit}
    title="Streamline Your Parking Experience"
    description="The best way to park"
   >
       
       <ButtomSocial /> 
    <Form.SubmitButton
    buttonText="Sign up with your email"
    //isLoading={isLoading}
    />
   </Form>
   </>
  )
}

export default PricipalPage