const config = {
    verbose: true,
        transform: { "\\.ts$": ['ts-jest'] },
        preset: 'react-native',
        moduleFileExtensions: ['ts', 'tsx', 'js', 'jsx', 'json', 'node'],
        transformIgnorePatterns: [
            "node_modules/(?!(react-native"
            + "|react-navigation-tabs"
            + "|react-native-splash-screen"
            + "|react-native-screens"
            + "|react-native-reanimated"
            + ")/)"]
  };

export default config;