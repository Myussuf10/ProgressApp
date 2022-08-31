import 'react-native-gesture-handler';
import React, { useState } from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import { NavigationContainer } from '@react-navigation/native';
import {

  StyleSheet,

} from 'react-native';
import Login from './components/Login.js';
import Comments from './components/Forms/Comments.js';
import ForgotPassword from './components/ForgotPassword.js';
import Parent from './components/Parent.js';
import SignUp from './components/Forms/SignUp.js';
import AddParent from './components/AddParent.js';
import Admin from './components/Admin.js';
import AddTeacher from './components/AddTeacher.js';
import TeacherHome from './components/TeacherHome.js';
import SetUpClasses from './components/SetUpClasses.js';
import NewSubject from './components/NewSubject';
import Attendance from './components/util/Attendance.js';
import TeachingPage from './components/util/TeachingPage.js';
import Header from './components/Header.js';
import { useContext } from 'react';
import AuthProvider, {AuthContext} from './components/store/AuthContext.js';

const Stack = createStackNavigator();

const globalScreenOptions = {
  headerStyle: { backgroundColor: '#608d56' },
  HeaderTitleStyle: { color: 'white' },
  headerTintColor: 'white',
};
function AuthStack() {
  return (
    <Stack.Navigator screenOptions={globalScreenOptions} initialRouteName="Login">
      <Stack.Screen name="Login" component={Login} />
    </Stack.Navigator>
  )
}

function AdminStack() {
  return (
    <Stack.Navigator screenOptions={globalScreenOptions}>

    </Stack.Navigator>
  )

}

function TeacherStack() {
  return (
    <Stack.Navigator screenOptions={globalScreenOptions}>
      <Stack.Screen name="TeacherHome" component={TeacherHome} />
      <Stack.Screen name='Comments' component={Comments}/>
      <Stack.Screen name="TeachingPage" component={TeachingPage} />

    </Stack.Navigator>
  )
}

const RoleViews = {
  ROLE_ADMIN: AdminStack,
  ROLE_TEACHER: TeacherStack,
}

function Navigation() {
  const authCtx = useContext(AuthContext);

  const RoleSpecificView = RoleViews[authCtx.role] && AuthStack;
  return (
    <NavigationContainer>
      {/* {!authCtx.isAuthenticated && <AuthStack />}
      
      {authCtx.isAuthenticated &&  <TeacherStack/>} */}
      <RoleSpecificView/>
    </NavigationContainer>
  )
}
export default function App() {




  return (
    // <AuthContext>
    //   <NavigationContainer>
    //     <AuthStack />
    //     {/* <Stack.Navigator screenOptions={globalScreenOptions} initialRouteName="Login">
    //     <Stack.Screen name="Header" component={Header} />
    //     <Stack.Screen name="NewSubject" options={{ title: "New Subject" }} component={NewSubject} />
    //     <Stack.Screen name="Parent" component={Parent} />
    //     <Stack.Screen name="Admin" component={Admin} />
    //     <Stack.Screen name="Attendance" component={Attendance} />
    //     <Stack.Screen name="SignUp" component={SignUp} />
    //     <Stack.Screen name="AddParent" component={AddParent} />
    //     <Stack.Screen name="AddTeacher" component={AddTeacher} />
    //     <Stack.Screen name="ForgotPassword" component={ForgotPassword} />
    //     <Stack.Screen name="SetUpClasses" options={{ title: "Classes" }} component={SetUpClasses} />
    //   </Stack.Navigator> */}
    //   </NavigationContainer>
    // </AuthContext>
    <AuthProvider>
      <Navigation />
    </AuthProvider>

  );
};

const styles = StyleSheet.create({});

