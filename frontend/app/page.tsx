"use client";

import MainLayout from "@/components/layout/MainLayout";
import Button from "@/components/ui/button";
import Input from "@/components/ui/input";
import Card from "@/components/ui/card";

export default function Home() {
  return (
    <MainLayout>
      <h1 className="text-3xl font-bold mb-6">
        Welcome to Makeup Booking 💄
      </h1>

      <Card>
        <div className="flex flex-col gap-4">
          <Input label="Name" placeholder="Enter your name" />
          <Input label="Email" placeholder="Enter email" />

          <div className="flex gap-2">
            <Button>Primary</Button>
            <Button variant="secondary">Secondary</Button>
          </div>
        </div>
      </Card>
    </MainLayout>
  );
}