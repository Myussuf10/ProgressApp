import 'react-native-gesture-handler';
import React, { useState } from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import { NavigationContainer } from '@react-navigation/native';
import {

  StyleSheet, Text,

} from 'react-native';
import Login from './components/Login.js';
import Comments from './components/Forms/Comments.js';
import ForgotPassword from './components/ForgotPassword.js';
import ParentsHome from './components/ParentsHome'
import Parent from './components/Parent.js';
import SignUp from './components/Forms/SignUp.js';
import AddParent from './components/AddParent.js';
import Admin from './components/Admin.js';
import AddTeacher from './components/AddTeacher.js';
import TeacherHome from './components/TeacherHome.js';
import SetUpClasses from './components/SetUpClasses.js';
import NewSubject from './components/NewSubject';
import Attendance from './components/util/Attendance.js';
import Progress from './components/Progress.js';
import { useContext } from 'react';
import SubjectPage from './components/SubjectPage.js';
import AuthProvider from './components/store/AuthContext.js';
import { AuthContext } from './components/store/AuthContext.js'
import LoadingOverlay from './components/util/LoadingSpinner.js';
import Icon from 'react-native-vector-icons/MaterialIcons';
import AsyncStorage from '@react-native-async-storage/async-storage';
import Subject from './components/Subject.js';
import AttendanceParent from './components/AttendanceParent.js';
import SubjectTeacher from './components/util/SubjectTeacher.js';
import Classess from './components/util/Classess.js';
import SetProgress from './components/util/SetProgress.js';
import SubjectStudent from './components/SubjectStudent.js';
import CommentList from './components/util/CommentList.js';
import CommentParent from './components/CommentParent.js';
import AddAdmin from './components/AddAdmin.js';


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

function ParentStack() {
  const authCtx = useContext(AuthContext)
  return (
    <Stack.Navigator screenOptions={globalScreenOptions}>
      <Stack.Screen name="Parent's Home Page" component={ParentsHome} options={{
        headerRight: () => <Icon name='logout' size={30} onPress={authCtx.logout} />
      }} />
      <Stack.Screen name= "SubjectPage" component={SubjectPage}/>
      <Stack.Screen name="Subject" component={Subject}/>
      <Stack.Screen name="Progress" component={Progress}/>
      <Stack.Screen name='AttendanceParent' component={AttendanceParent}/>
      <Stack.Screen name='CommentParent' component={CommentParent}/>

    </Stack.Navigator>
  )}
function AdminStack() {
  const authCtx = useContext(AuthContext)
  return (
    <Stack.Navigator screenOptions={globalScreenOptions}>
      <Stack.Screen name="Admin" component={Admin} options={{
        headerRight: () => <Icon name='logout' size={30} onPress={authCtx.logout} />
      }} />
      <Stack.Screen name="SignUp" component={SignUp} />
      <Stack.Screen name="AddParent" component={AddParent} />
      <Stack.Screen name="AddTeacher" component={AddTeacher} />
      <Stack.Screen name="Parent" component={Parent} />
      <Stack.Screen name="NewSubject" options={{ title: "New Subject" }} component={NewSubject} />
      <Stack.Screen name="SetUpClasses" options={{ title: "Classes" }} component={SetUpClasses} />
      <Stack.Screen name='SubjectStudent' options={{title: "Assing Subject"}} component={SubjectStudent}/>
      <Stack.Screen name='AddAdmin' component={AddAdmin}/>
    </Stack.Navigator>
  )}

function TeacherStack() {
  const authCtx = useContext(AuthContext)
  return (
    <Stack.Navigator screenOptions={globalScreenOptions}>
      <Stack.Screen name="Teacher's Home Page" component={TeacherHome} options={{
        headerRight: () => <Icon name='logout' size={30} onPress={authCtx.logout} />
      }} />
      <Stack.Screen name='Comments' component={Comments} />
      <Stack.Screen name="SetProgress" component={SetProgress}/>
      <Stack.Screen name="Attendance" component={Attendance} />
      <Stack.Screen name= "Classess" component={Classess}/>
      <Stack.Screen name='SubjectTeacher' component={SubjectTeacher}/>
      <Stack.Screen name= "SubjectPage" component={SubjectPage}/>
      <Stack.Screen name="CommentList" component={CommentList}/>
    </Stack.Navigator>
  )}

function Navigation() {
  const authCtx = useContext(AuthContext);

  const RoleViews = {
    ROLE_ADMIN: AdminStack,
    ROLE_TEACHER: TeacherStack,
    ROLE_PARENT: ParentStack,
    Auth: AuthStack
  }

  let role = authCtx.role
  const RoleSpecificView = (role === "ROLE_TEACHER") ? RoleViews["ROLE_TEACHER"]
    : (role === "ROLE_PARENT") ? RoleViews["ROLE_PARENT"] : RoleViews["ROLE_ADMIN"];

  //const RoleSpecificView = RoleViews["ROLE_TEACHER"] ?? <AuthStack/>

  return (

    <NavigationContainer>
      {!authCtx.isAuthenticated && <AuthStack />}
      {authCtx.isAuthenticated && <RoleSpecificView />}
    </NavigationContainer>
  )
}
export default function App() {
  return (
    // <AuthContext>
    //   <NavigationContainer>
    //     <AuthStack />
    //     {/* <Stack.Navigator screenOptions={globalScreenOptions} initialRouteName="Login">
    //     <Stack.Screen name="ForgotPassword" component={ForgotPassword} />
    //   </Stack.Navigator> */}
    //   </NavigationContainer>
    // </AuthContext>
    <AuthProvider>
      <Navigation />
    </AuthProvider>

  );
};

const styles = StyleSheet.create({});

