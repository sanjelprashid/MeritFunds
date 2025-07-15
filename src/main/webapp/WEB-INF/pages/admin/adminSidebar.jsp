<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
/* Sidebar Styles */
.sidebar {
  background-color: #1E3A8A; /* Royal Blue base */
  background-image: linear-gradient(to bottom, #1E3A8A, #2563EB);
  color: #fff;
  width: 260px;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.15);
  display: flex;
  flex-direction: column;
  z-index: 100;
}

.sidebar-header {
  padding: 1.5rem;
  text-align: center;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.sidebar-header h2 {
  margin: 0;
  font-size: 1.5rem;
  font-weight: 600;
  letter-spacing: 0.5px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo-accent {
  color: #93C5FD; /* Light blue accent */
  font-weight: 700;
  margin-left: 4px;
}

.sidebar-menu {
  padding: 1rem 0;
  flex-grow: 1;
  overflow-y: auto;
}

.sidebar-menu ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar-menu li {
  margin: 0.25rem 0.75rem;
  border-radius: 6px;
  overflow: hidden;
}

.sidebar-menu a {
  color: #fff;
  text-decoration: none;
  display: flex;
  align-items: center;
  padding: 0.75rem 1rem;
  transition: all 0.2s ease;
  font-weight: 500;
  position: relative;
  border-radius: 6px;
}

.sidebar-menu a:hover {
  background-color: rgba(255, 255, 255, 0.1);
  transform: translateX(4px);
}

.sidebar-menu a.active {
  background-color: rgba(255, 255, 255, 0.15);
  border-left: 3px solid #93C5FD;
}

/* Icon placeholders - you can replace with actual icons */
.menu-icon {
  width: 20px;
  height: 20px;
  margin-right: 12px;
  opacity: 0.85;
  display: inline-flex;
  align-items: center;
  justify-content: center;
}

.sidebar-footer {
  padding: 1rem 1.5rem;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

.sidebar-footer .btn-logout {
  display: block;
  width: 100%;
  padding: 0.75rem;
  text-align: center;
  color: #fff;
  background-color: rgba(255, 255, 255, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.2);
  border-radius: 6px;
  text-decoration: none;
  transition: all 0.2s ease;
  font-weight: 500;
}

.sidebar-footer .btn-logout:hover {
  background-color: #DBEAFE;
  color: #1E3A8A;
  border-color: #DBEAFE;
  transform: translateY(-2px);
}

.sidebar-footer .btn-logout:active {
  transform: translateY(0);
}

.section-label {
  font-size: 0.75rem;
  text-transform: uppercase;
  letter-spacing: 1px;
  color: rgba(255, 255, 255, 0.5);
  padding: 1rem 1.5rem 0.5rem;
  font-weight: 600;
}

/* Responsive adjustments for sidebar */
@media (max-width: 768px) {
  .sidebar {
    width: 100%;
    height: auto;
    position: relative;
  }
  
  .sidebar-header {
    padding: 1rem;
  }
  
  .sidebar-menu {
    padding: 0.5rem 0;
  }
  
  .sidebar-menu li {
    margin: 0.25rem 0.5rem;
  }
  
  .sidebar-footer {
    position: relative;
  }
}
</style>

<div class="sidebar">
  <div class="sidebar-header">
    <h2>Admin <span class="logo-accent">Panel</span></h2>
  </div>
  
  <div class="sidebar-menu">
    <div class="section-label">Management</div>
    <ul>
      <li>
        <a href="<%= request.getContextPath() %>/admin/dashboard" class="active">
          <span class="menu-icon">📊</span>Dashboard
        </a>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/admin/users">
          <span class="menu-icon">👥</span>Manage Users
        </a>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/admin/scholarships">
          <span class="menu-icon">🎓</span>Manage Scholarships
        </a>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/admin/applications">
          <span class="menu-icon">📝</span>View Applications
        </a>
      </li>
      <!-- Add more admin links here -->
    </ul>
    
    <div class="section-label">Settings</div>
    <ul>
      <li>
        <a href="<%= request.getContextPath() %>/admin/settings">
          <span class="menu-icon">⚙️</span>System Settings
        </a>
      </li>
      <li>
        <a href="<%= request.getContextPath() %>/admin/profile">
          <span class="menu-icon">👤</span>My Profile
        </a>
      </li>
    </ul>
  </div>
  
  <div class="sidebar-footer">
    <a href="<%= request.getContextPath() %>/logout" class="btn-logout">Logout</a>
  </div>
</div>