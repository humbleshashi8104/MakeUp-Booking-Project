"use client";

import { useState } from "react";
import MainLayout from "@/components/layout/MainLayout";
import Input from "@/components/ui/input";
import Button from "@/components/ui/button";
import { signupUser } from "@/modules/auth/api/authApi";
import { useRouter } from "next/navigation";

export default function SignupPage() {
  const router = useRouter();

  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    phone: "",
  });

  const handleChange = (e: any) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSignup = async () => {
    try {
      await signupUser(form);

      alert("Signup successful!");

      router.push("/login");
    } catch (err) {
      alert("Signup failed");
    }
  };

  return (
    <MainLayout>
      <div className="max-w-md mx-auto mt-10">
        <h1 className="text-2xl font-bold mb-6">Signup</h1>

        <div className="flex flex-col gap-4">
          <Input label="Name" name="name" onChange={handleChange} />
          <Input label="Email" name="email" onChange={handleChange} />
          <Input label="Phone" name="phone" onChange={handleChange} />
          <Input
            label="Password"
            type="password"
            name="password"
            onChange={handleChange}
          />

          <Button onClick={handleSignup}>Signup</Button>
        </div>
      </div>
    </MainLayout>
  );
}