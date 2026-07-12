🚛 TransitOps

Full-Stack Transport Operations Management System

Fleet, drivers, trips, maintenance, fuel, and expenses — all from one centralized platform.



📖 Overview

Transport operations often involve managing multiple vehicles, drivers, trips, maintenance schedules, fuel records, and operational expenses across disconnected systems.
TransitOps brings it all together into a single, centralized platform offering role-based access control, secure JWT authentication, real-time operational dashboards, and structured workflows for day-to-day transport operations.



✨ Features

🔐 Authentication & Security


Secure user login with JWT-based authentication
Access token and refresh token support
BCrypt password encryption
Role-Based Access Control (RBAC)
Permission-based authorization architecture
Secure, protected API endpoints


🚚 Fleet Management


Add, view, and update vehicles in the fleet inventory
Update vehicle operational status
Track odometer readings, load capacity, and acquisition cost
Manage the full vehicle lifecycle

<img width="803" height="292" alt="image" src="https://github.com/user-attachments/assets/837590e4-cd8f-4854-9602-7a332ccd9d21" />


🧑‍✈️ Driver Management


Add and manage driver records
Monitor driver availability and status
Support driver compliance workflows


<img width="846" height="292" alt="image" src="https://github.com/user-attachments/assets/09906b9f-a62a-46a7-b961-eaa03f8c361a" />



🗺️ Trip Management


Create trips, assign vehicles and drivers
Record source/destination and cargo weight
Track planned vs. actual distance
Dispatch, complete, or cancel trips
Record fuel usage and start/end odometer readings

<img width="827" height="280" alt="image" src="https://github.com/user-attachments/assets/5d2da8f4-e1b5-4f55-a8db-539e9b390869" />


🔧 Maintenance Management


Create and track maintenance records
Monitor maintenance status and cost
Manage vehicle availability during maintenance


⛽ Fuel Management


Record and associate fuel logs with vehicles
Track fuel consumption and cost
Foundation for fuel-efficiency analytics


💰 Expense Management


Record and categorize operational expenses
Associate expenses with fleet operations
Support operational cost analysis


📊 Dashboard

A real-time snapshot of your operations:


🚛 Total / Active / Available / In-Maintenance / Retired vehicles
🧑‍✈️ Total / Available / On-Duty / Suspended drivers
🗺️ Total / Active / Pending / Completed / Cancelled trips
📈 Fleet utilization percentage


📈 Reports & Analytics


Fleet utilization analysis
Fuel efficiency & cost analysis
Maintenance cost analysis
Operational expense analysis
Vehicle-level and fleet-level performance reporting



🔐 Role-Based Access Control

TransitOps restricts access according to a user's responsibilities, following the architecture:

User → Role → Permissions

A user's permissions are determined by the role assigned to their account.



<img width="830" height="372" alt="image" src="https://github.com/user-attachments/assets/9a3a21ce-7f78-4671-b94f-37e2821de184" />


