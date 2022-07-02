import 'react-native-gesture-handler';
import React, {useState} from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import {NavigationContainer} from '@react-navigation/native';
import {
  KeyboardAvoidingView,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  useColorScheme,
  View,
} from 'react-native';
import Login from './components/Login.js';
import ForgotPassword from './components/ForgotPassword.js';
import Parent from './components/Parent.js';
import SignUp from './components/Forms/SignUp.js';

const Stack = createStackNavigator();

const App = () => {
  const [email, setEmail] = useState(' ');
  const [password, setPassword] = useState(' ');

  const globalScreenOptions = {
    headerStyle: {backgroundColor: '#608d56'},
    HeaderTitleStyle: {color: 'white'},
    headerTintColor: 'white',
  };

  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={globalScreenOptions} initialRouteName="Login">
        <Stack.Screen name="Login" component={Login} />
        <Stack.Screen name="Parent" component={Parent} />
        <Stack.Screen name="SignUp" component={SignUp} />
        <Stack.Screen name="ForgotPassword" component={ForgotPassword} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

const styles = StyleSheet.create({});

export default App;