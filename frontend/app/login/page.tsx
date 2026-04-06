"use client";

import { useState } from "react";
import MainLayout from "@/components/layout/MainLayout";
import Input from "@/components/ui/input";
import Button from "@/components/ui/button";
import { loginUser } from "@/modules/auth/api/authApi";
import { useRouter } from "next/navigation";

export default function LoginPage() {
  const router = useRouter();

  const [form, setForm] = useState({
    email: "",
    password: "",
  });

  const handleChange = (e: any) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleLogin = async () => {
    try {
      const res = await loginUser(form);

      // Save token
      localStorage.setItem("token", res.token);

      alert("Login successful!");

      router.push("/services");
    } catch (err) {
      alert("Login failed");
    }
  };

  return (
    <MainLayout>
      <div className="max-w-md mx-auto mt-10">
        <h1 className="text-2xl font-bold mb-6">Login</h1>

        <div className="flex flex-col gap-4">
          <Input
            label="Email"
            name="email"
            value={form.email}
            onChange={handleChange}
            placeholder="Enter email"
          />

          <Input
            label="Password"
            type="password"
            name="password"
            value={form.password}
            onChange={handleChange}
            placeholder="Enter password"
          />

          <Button onClick={handleLogin}>Login</Button>
        </div>
      </div>
    </MainLayout>
  );
}