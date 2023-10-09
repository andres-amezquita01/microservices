"use client";
import { Form } from "@/components/HeaderRegister";

function RegisterPage() {
  const RegisterSubmit = async (formData: any) => {};

  return (
    <div className="container mx-auto px-4" datatype="RegisterPage">
      <Form
        onSubmit={RegisterSubmit}
        title="Register"
        description="Don’t have an account?"
      >
        {/* Link para crear una cuenta nueva */}
        <div className="flex justify-center mb-4">
          <Form.LinkText link="#" textLink="Create a new one" />
        </div>

        {/* Formulario de Registro */}
        <div className="my-2 flex flex-wrap justify-between md:space-x-4">
          {/* Columna izquierda */}
          <div className="w-full md:w-5/12 space-y-4 md:pr-4">
            <Form.Input
              name="fistName"
              label="First Name"
              placeholder="First Name"
            />
            <Form.Input
              name="lastName"
              label="Last Name"
              placeholder="Last Name"
            />
            <Form.Input
              name="identificationCode"
              label="Identification Code"
              placeholder="Enter your Identification code"
            />
            <Form.Input
              name="phoneNumber"
              label="Phone Number"
              placeholder="Enter your phone number"
            />
          </div>

          {/* Columna derecha */}
          <div className="w-full md:w-5/12 space-y-4 md:pl-4 mt-4 md:mt-0">
            <Form.Input
              name="userName"
              label="User Name"
              placeholder="Choose a user name"
            />
            <Form.Input
              name="email"
              label="Email"
              placeholder="youremail@company.com"
            />
            <Form.Input
              placeholder="Choose a password"
              label="Password"
              name="Password"
              type="password"
            />
            <Form.Input
              placeholder="Confirm Password"
              label="Confirm your password"
              name="Password"
              type="password"
            />
          </div>
        </div>

        {/* Botón de Continuar */}
        <div className="mt-4">
          <Form.SubmitButton
            buttonText="Continue"
            // Aquí puedes ajustar el tamaño del botón si es necesario
          />
        </div>
      </Form>
    </div>
  );
}

export default RegisterPage;
